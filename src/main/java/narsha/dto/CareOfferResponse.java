package narsha.dto;

import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import narsha.entity.CareOffer;

@Getter
@Setter
@Schema(description = "Care Offer Response")
public class CareOfferResponse {

    @Schema(description = "Offer ID", example = "1")
    private Long id;

    @Schema(description = "Caregiver ID", example = "1")
    private Long caregiverId;

    @Schema(description = "Patient ID", example = "2")
    private Long patientId;

    @Schema(description = "Caregiver Name", example = "John Doe")
    private String caregiverName;

    @Schema(description = "Patient Name", example = "Jane Doe")
    private String patientName;

    @Schema(description = "Daily Rate", example = "100.0")
    private double dailyRate;

    @Schema(description = "Total Amount", example = "1000.0")
    private double totalAmount;

    @Schema(description = "Accepted", example = "false")
    private boolean accepted;

    public CareOfferResponse(CareOffer careOffer) {
        this.id = careOffer.getId();
        this.caregiverId = careOffer.getCaregiver().getId();
        this.patientId = careOffer.getPatient().getId();
        this.caregiverName = careOffer.getCaregiver().getName();
        this.patientName = careOffer.getPatient().getName();
        this.dailyRate = careOffer.getDailyRate();
        this.totalAmount = careOffer.getTotalAmount();
        this.accepted = careOffer.isAccepted();
    }
}
