package narsha.dto.gujik;

import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostRequest;
import narsha.entity.Caregiver;
import narsha.entity.Gujik;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구직 요청")
public class GujikRequest extends PostRequest<Gujik, Caregiver> {

    public GujikRequest(String title, String content) {
        super(title, content);
    }

    @Override
    public Gujik toEntity(Caregiver author) {
        return new Gujik(getTitle(), getContent(), author);
    }
}
