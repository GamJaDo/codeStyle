package narsha.dto.patient;

import lombok.Getter;
import lombok.Setter;
import narsha.dto.UserInfoResponse;
import narsha.entity.PatientMedRecord;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "환자 정보 응답")
public class PatientInfoResponse extends UserInfoResponse {

    @Schema(description = "키", example = "175")
    private int height;

    @Schema(description = "몸무게", example = "70")
    private int weight;

    @Schema(description = "환자 의료 기록")
    private PatientMedRecord patientMedRecord;

    public PatientInfoResponse(String patientAccount, String patientName, String profileImageUrl, int patientAge,
                               int patientHeight, int patientWeight, int patientGender, String patientLocation, PatientMedRecord patientMedRecord) {
        super(patientAccount, patientName, profileImageUrl, patientAge, patientGender, patientLocation);
        this.height = patientHeight;
        this.weight = patientWeight;
        this.patientMedRecord = patientMedRecord;
    }
}
