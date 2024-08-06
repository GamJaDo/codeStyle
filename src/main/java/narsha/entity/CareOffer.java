package narsha.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import narsha.dto.CareOfferResponse;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "care_offer")
public class CareOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caregiver_id", nullable = false)
    private Caregiver caregiver;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private double dailyRate;
    private double totalAmount;
    private boolean accepted;

    public CareOffer(Caregiver caregiver, Patient patient, double dailyRate, double totalAmount) {
        this.caregiver = caregiver;
        this.patient = patient;
        this.dailyRate = dailyRate;
        this.totalAmount = totalAmount;
        this.accepted = false;
    }

    public CareOfferResponse toDto() {
        return new CareOfferResponse(this);
    }
}
