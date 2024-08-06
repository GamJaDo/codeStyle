package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import narsha.entity.CareEnrollment;
import narsha.entity.Caregiver;
import narsha.entity.Patient;

@Getter
@Setter
@Schema(description = "Care Enrollment Request")
public class CareEnrollmentRequest {

    @Schema(description = "Caregiver ID", example = "1")
    private Long caregiverId;

    @Schema(description = "Patient ID", example = "2")
    private Long patientId;

    public CareEnrollment toEntity(Caregiver caregiver, Patient patient) {
        return new CareEnrollment(caregiver, patient);
    }
}
