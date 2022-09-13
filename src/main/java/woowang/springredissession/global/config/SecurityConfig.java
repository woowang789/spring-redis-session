package woowang.springredissession.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin().and()
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers( "/login", "/signup","/","/h2-console/**").permitAll() // 누구나 접근 가능
                .antMatchers("/admin/**").hasAnyRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated(); // 나머지 요청은 권한이 있어야함

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        // 인증 필요시 로그인 페이지와 로그인 성공시 리다이랙팅 경로 지정
        http.formLogin()
                .loginPage("/login")    // 로그인 페이지 링크
                .failureHandler((req, res, e) -> System.out.println(e))
                .defaultSuccessUrl("/", true); // 로그인 성공 후 리다이렉트 주소

        http.userDetailsService(userDetailsService);
    }

}
