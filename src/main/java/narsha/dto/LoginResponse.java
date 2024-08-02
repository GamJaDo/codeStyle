package narsha.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginResponse {

    private String email;

    private String password;

    public LoginResponse(String email, String password){
        this.email = email;
        this.password = password;
    }

}
