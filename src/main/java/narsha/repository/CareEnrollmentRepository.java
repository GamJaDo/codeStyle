package narsha.repository;

import narsha.entity.CareEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareEnrollmentRepository extends JpaRepository<CareEnrollment, Long> {
}
