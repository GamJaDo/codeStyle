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
import narsha.dto.CareEnrollmentResponse;

@Getter
@Setter
@Entity
@Table(name = "care_enrollments")
public class CareEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "gujik_id", nullable = false)
    private Gujik gujik;

    private boolean approved;

    public CareEnrollment() {}

    public CareEnrollment(Patient patient, Gujik gujik) {
        this.patient = patient;
        this.gujik = gujik;
        this.approved = false;
    }

    public CareEnrollmentResponse toDto() {
        return new CareEnrollmentResponse(
            this.id,
            this.patient.getId(),
            this.patient.getName(),
            this.approved
        );
    }
}
