package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.CareEnrollment;
import narsha.entity.Gujik;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 등록 요청")
public class CareEnrollmentRequest {
    
    @Schema(description = "구직 ID", example = "1")
    private Long gujikId;

    public CareEnrollment toEntity(Patient patient, Gujik gujik) {
        return new CareEnrollment(patient, gujik);
    }
}
