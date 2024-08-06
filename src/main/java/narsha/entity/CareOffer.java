package narsha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.CareOfferResponse;

@Getter
@Setter
@Entity
@Table(name = "care_offers")
public class CareOffer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    private int dailyRate;
    private int totalAmount;

    public CareOffer() {}

    public CareOffer(Patient patient, Caregiver caregiver, int dailyRate, int totalAmount) {
        this.patient = patient;
        this.caregiver = caregiver;
        this.dailyRate = dailyRate;
        this.totalAmount = totalAmount;
    }
    
    public CareOfferResponse toDto() {
        return new CareOfferResponse(
            this.caregiver.getName(),
            this.caregiver.getProfileImageUrl(),
            this.dailyRate,
            this.totalAmount
        );
    }
}
