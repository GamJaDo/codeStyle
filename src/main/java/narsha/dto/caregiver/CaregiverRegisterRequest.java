package narsha.dto.caregiver;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.AbstractRegisterRequest;
import narsha.entity.Caregiver;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제공자 등록 요청")
public class CaregiverRegisterRequest extends AbstractRegisterRequest<Caregiver> {

    @Override
    public Caregiver toEntity() {
        return new Caregiver(getAccount(), getPassword(), getName());
    }
}
