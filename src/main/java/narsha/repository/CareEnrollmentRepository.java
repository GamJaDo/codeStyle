package narsha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narsha.entity.CareEnrollment;
import narsha.entity.Gujik;
import narsha.entity.Patient;

@Repository
public interface CareEnrollmentRepository extends JpaRepository<CareEnrollment, Long> {

	List<CareEnrollment> findByGujikAuthorId(Long caregiverId);
    boolean existsByPatientAndGujik(Patient patient, Gujik gujik);
}
