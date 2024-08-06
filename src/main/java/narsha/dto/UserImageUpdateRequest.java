package narsha.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "사용자 이미지 업데이트 요청")
public class UserImageUpdateRequest {

    @Schema(description = "새 이미지 파일")
    private MultipartFile newImage;
}
