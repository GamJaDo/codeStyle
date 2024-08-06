package narsha.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import narsha.exception.InvalidRegisterException;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 등록 요청")
public abstract class AbstractRegisterRequest<T> {

    @NotBlank(message = "아이디 입력은 필수입니다.")
    @Schema(description = "사용자 계정", example = "user123")
    private String account;

    @NotBlank(message = "비밀번호 입력은 필수 입니다.")
    @Schema(description = "비밀번호", example = "password123!")
    private String password;

    @NotBlank(message = "비밀번호 확인 입력은 필수 입니다.")
    @Schema(description = "비밀번호 확인", example = "password123!")
    private String passwordConfirm;

    @NotBlank(message = "이름 입력은 필수 입니다.")
    @Schema(description = "이름", example = "홍길동")
    private String name;
    
    public void passwordMatch() {
        if (!password.equals(passwordConfirm)) {
            throw new InvalidRegisterException("Passwords do not match.");
        }
    }
    
    public abstract T toEntity();
}
