package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 이름 업데이트 요청")
public class UserNameUpdateRequest {

    @Schema(description = "새 이름", example = "홍길순")
    private String newName;
}
