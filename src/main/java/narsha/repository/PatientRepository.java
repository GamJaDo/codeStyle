package narsha.repository;

import org.springframework.stereotype.Repository;

import narsha.entity.Patient;

@Repository
public interface PatientRepository extends UserRepository<Patient> {
	
}
