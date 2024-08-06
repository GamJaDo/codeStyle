package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 등록 응답")
public class CareEnrollmentResponse {

    @Schema(description = "등록 ID", example = "1")
    private Long id;

    @Schema(description = "환자 ID", example = "2")
    private Long patientId;

    @Schema(description = "환자 이름", example = "홍길동")
    private String patientName;

    @Schema(description = "승인 여부", example = "true")
    private boolean approved;

    public CareEnrollmentResponse(Long id, Long patientId, String patientName, boolean approved) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.approved = approved;
    }
}
