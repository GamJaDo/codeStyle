package narsha.dto.gujik;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostResponse;
import narsha.entity.Caregiver;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구직 응답")
public class GujikResponse extends PostResponse<Caregiver> {

    public GujikResponse(Long id, String title, String content, String imageUrl, LocalDateTime createdAt, Caregiver author) {
        super(id, title, content, imageUrl, createdAt, author);
    }
}
