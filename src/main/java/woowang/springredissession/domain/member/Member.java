package woowang.springredissession.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;
    private String nick;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @Builder
    public Member(String email, String nick, String password,Role role) {
        this.email = email;
        this.nick = nick;
        this.password = password;
        this.role = role;
    }
}
