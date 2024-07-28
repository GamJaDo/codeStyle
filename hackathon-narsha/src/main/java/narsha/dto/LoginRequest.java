package narsha.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import narsha.entity.User;
import narsha.exception.InvalidRegisterException;

@Getter
@Setter
public class LoginRequest {
	
	@Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;
    
    public void passwordMatch(User user) {
		if (!this.password.equals(user.getPassword())) {
			throw new InvalidRegisterException("Passwords do not match.");
		}
	}
}
