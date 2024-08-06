package narsha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.patient.PatientInfoResponse;
import narsha.dto.patient.PatientProfileUpdateRequest;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient extends User {

	private int height;
	private int weight;

	@ManyToOne
	@JoinColumn(name = "patientInfo_id")
	PatientMedRecord patientInfo;

	public Patient() {
		super();
	}

	public Patient(String account, String password, String name) {
		super(account, password, name);
	}
	
	public Patient(PatientProfileUpdateRequest request) {
        super();
        this.height = request.getHeight();
        this.weight = request.getWeight();
        this.patientInfo = request.getMedRecord();
    }

	public PatientInfoResponse toDto() {
		return new PatientInfoResponse(getAccount(), getName(), getProfileImageUrl(), getAge(), getHeight(),
				getWeight(), getGender(), getLocation(), patientInfo);
	}
}
