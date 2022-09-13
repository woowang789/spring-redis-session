package woowang.springredissession.domain.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
