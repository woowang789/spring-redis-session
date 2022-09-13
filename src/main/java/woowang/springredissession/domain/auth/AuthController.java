package woowang.springredissession.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import woowang.springredissession.domain.member.Member;
import woowang.springredissession.domain.member.MemberRepository;
import woowang.springredissession.domain.member.Role;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {

    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String signinForm(Model model) {
        model.addAttribute("member", new LoginRequest());
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("member", new RegistryRequest());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute RegistryRequest registryRequest) {
        Member member = Member.builder()
                .email(registryRequest.getEmail())
                .password(registryRequest.getPassword())
                .nick(registryRequest.getNick())
                .role(registryRequest.getRole())
                .build();
        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/admin/home")
    public ResponseEntity<String> adminPage(){
        return ResponseEntity.ok("ok");
    }



    @ModelAttribute("roles")
    public Map<String, Role> roles() {
        Map<String, Role> map = new LinkedHashMap<>();
        map.put("관리자", Role.ROLE_ADMIN);
        map.put("일반 사용자", Role.ROLE_MEMBER);
        return map;
    }
}
