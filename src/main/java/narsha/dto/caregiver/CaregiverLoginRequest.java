package narsha.dto.caregiver;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.UserLoginRequest;
import narsha.entity.Caregiver;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제공자 로그인 요청")
public class CaregiverLoginRequest extends UserLoginRequest<Caregiver> {
    
}
