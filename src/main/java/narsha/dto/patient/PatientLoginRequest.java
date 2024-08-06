package narsha.dto.patient;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.UserLoginRequest;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "환자 로그인 요청")
public class PatientLoginRequest extends UserLoginRequest<Patient> {
    
}
