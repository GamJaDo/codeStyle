package narsha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import narsha.entity.CareOffer;

@Repository
public interface CareOfferRepository extends JpaRepository<CareOffer, Long> {
    Optional<CareOffer> findByPatientId(Long patientId);
}
