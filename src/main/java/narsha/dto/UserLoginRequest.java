package narsha.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import narsha.entity.User;
import narsha.exception.InvalidLoginException;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 로그인 요청")
public class UserLoginRequest<T extends User> {

    @NotBlank(message = "아이디 입력은 필수입니다.")
    @Schema(description = "사용자 계정", example = "user123")
    private String account;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Schema(description = "비밀번호", example = "password123!")
    private String password;

    public void passwordMatch(T user) {
        if (!password.equals(user.getPassword())) {
            throw new InvalidLoginException("Passwords do not match.");
        }
    }
}
