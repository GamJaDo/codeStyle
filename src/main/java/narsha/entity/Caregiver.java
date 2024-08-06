package narsha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import narsha.dto.caregiver.CaregiverInfoResponse;

@Getter
@Setter
@Entity
@Table(name = "caregiver")
public class Caregiver extends User {

	@ManyToOne
	@JoinColumn(name = "caregiverIntro_id")
	CaregiverIntro caregiverIntro;

	public Caregiver() {
		super();
	}

	public Caregiver(String account, String password, String name) {
		super(account, password, name);
	}

	public CaregiverInfoResponse toDto() {
		return new CaregiverInfoResponse(getAccount(), getName(), getProfileImageUrl(), getAge(), getGender(),
				getLocation(), caregiverIntro);
	}
}
