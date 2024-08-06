package narsha.dto.patient;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.AbstractRegisterRequest;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "환자 등록 요청")
public class PatientRegisterRequest extends AbstractRegisterRequest<Patient> {

    @Override
    public Patient toEntity() {
        return new Patient(getAccount(), getPassword(), getName());
    }
}
