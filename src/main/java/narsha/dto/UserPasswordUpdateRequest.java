package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.exception.InvalidRegisterException;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 비밀번호 업데이트 요청")
public class UserPasswordUpdateRequest {

    @Schema(description = "새 비밀번호", example = "newPassword123!")
    private String newPassword;

    @Schema(description = "새 비밀번호 확인", example = "newPassword123!")
    private String newPasswordConfirm;

    public void passwordMatch() {
        if (!newPassword.equals(newPasswordConfirm)) {
            throw new InvalidRegisterException("Passwords do not match.");
        }
    }
}
