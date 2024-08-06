package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import narsha.entity.CareOffer;
import narsha.entity.Caregiver;
import narsha.entity.Patient;

@Getter
@Setter
@Schema(description = "Care Offer Request")
public class CareOfferRequest {

    @Schema(description = "Caregiver ID", example = "1")
    private Long caregiverId;

    @Schema(description = "Patient ID", example = "2")
    private Long patientId;

    @Schema(description = "Daily Rate", example = "100.0")
    private double dailyRate;

    @Schema(description = "Total Amount", example = "1000.0")
    private double totalAmount;

    public CareOffer toEntity(Caregiver caregiver, Patient patient) {
        return new CareOffer(caregiver, patient, this.dailyRate, this.totalAmount);
    }
}
