package narsha.dto.patient;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.AbstractProfileUpdateRequest;
import narsha.entity.Patient;
import narsha.entity.PatientMedRecord;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "환자 프로필 업데이트 요청")
public class PatientProfileUpdateRequest extends AbstractProfileUpdateRequest<Patient> {

    @Schema(description = "키", example = "175")
    private int height;

    @Schema(description = "몸무게", example = "70")
    private int weight;

    @Schema(description = "의료 기록")
    private PatientMedRecord medRecord;

    public PatientProfileUpdateRequest(int age, int gender, String location) {
        super(age, gender, location);
    }

    @Override
    public Patient toEntity(Patient existingPatient) {
        existingPatient.setAge(getAge());
        existingPatient.setGender(getGender());
        existingPatient.setLocation(getLocation());
        existingPatient.setHeight(height);
        existingPatient.setWeight(weight);
        existingPatient.setPatientInfo(medRecord);
        return existingPatient;
    }
}
