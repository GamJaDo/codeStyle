package narsha.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import narsha.entity.User;
import narsha.exception.InvalidRegisterException;

@Getter
@Setter
public class RegisterRequest {

	@Email(message = "이메일 형식일 올바르지 않습니다.")
	@NotBlank(message = "이메일 입력은 필수 입니다.")
	private String email;
	
	@NotBlank(message = "비밀번호 입력은 필수 입니다.")
	private String password;
	
	@NotBlank(message = "비밀번호 입력은 필수 입니다.")
	private String passwordConfirm;
	
	public User toEntity() {
		return new User(this.email, this.password);				
	}
	
	public void passwordMatch() {
		if (!this.password.equals(this.passwordConfirm)) {
			throw new InvalidRegisterException("Passwords do not match.");
		}
	}
}
