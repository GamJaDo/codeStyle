package narsha.dto.guin;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.PostRequest;
import narsha.entity.Guin;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "구인 요청")
public class GuinRequest extends PostRequest<Guin, Patient> {

    public GuinRequest(String title, String content) {
        super(title, content);
    }

    @Override
    public Guin toEntity(Patient author) {
        return new Guin(getTitle(), getContent(), author);
    }
}
