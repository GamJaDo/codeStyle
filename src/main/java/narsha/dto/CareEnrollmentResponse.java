package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.CareEnrollment;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "Care Enrollment Response")
public class CareEnrollmentResponse {

    @Schema(description = "Enrollment ID", example = "1")
    private Long id;

    @Schema(description = "Caregiver ID", example = "1")
    private Long caregiverId;

    @Schema(description = "Patient ID", example = "2")
    private Long patientId;

    @Schema(description = "Caregiver Name", example = "John Doe")
    private String caregiverName;

    @Schema(description = "Patient Name", example = "Jane Doe")
    private String patientName;

    @Schema(description = "Approved", example = "false")
    private boolean approved;

    public CareEnrollmentResponse(CareEnrollment careEnrollment) {
        this.id = careEnrollment.getId();
        this.caregiverId = careEnrollment.getCaregiver().getId();
        this.patientId = careEnrollment.getPatient().getId();
        this.caregiverName = careEnrollment.getCaregiver().getName();
        this.patientName = careEnrollment.getPatient().getName();
        this.approved = careEnrollment.isApproved();
    }
}
