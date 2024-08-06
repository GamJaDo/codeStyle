package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import narsha.entity.CareOffer;
import narsha.entity.Caregiver;
import narsha.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "돌봄 제안 요청")
public class CareOfferRequest {

    @Schema(description = "일일 요금", example = "100000")
    private int dailyRate;

    @Schema(description = "총 금액", example = "700000")
    private int totalAmount;

    public CareOffer toEntity(Patient patient, Caregiver caregiver) {
        return new CareOffer(patient, caregiver, dailyRate, totalAmount);
    }
}
