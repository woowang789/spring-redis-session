package woowang.springredissession.domain.auth;

import lombok.Data;
import woowang.springredissession.domain.member.Role;

@Data
public class RegistryRequest {

    private String email;
    private String password;
    private String nick;
    private Role role = Role.ROLE_MEMBER;

}
