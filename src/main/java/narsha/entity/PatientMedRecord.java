package narsha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PatientInfo")
public class PatientMedRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	String startDay;
	String endDay;
	
	String diagnosis;
	
	String hospitalize;
	
	String ward;
	
	String movement;
	
	String divice;
	
	String drug;
}
