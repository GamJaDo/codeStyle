package narsha.dto.gujik;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import narsha.dto.PostRequest;
import narsha.entity.Caregiver;
import narsha.entity.Gujik;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@NoArgsConstructor
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
