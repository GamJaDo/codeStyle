package narsha.dto.guin;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostResponse;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구인 응답")
public class GuinResponse extends PostResponse<Patient> {

    public GuinResponse(Long id, String title, String content, String imageUrl, LocalDateTime createdAt, Patient author) {
        super(id, title, content, imageUrl, createdAt, author);
    }
}
