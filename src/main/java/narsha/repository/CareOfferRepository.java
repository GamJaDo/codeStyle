package narsha.repository;

import narsha.entity.CareOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CareOfferRepository extends JpaRepository<CareOffer, Long> {
    List<CareOffer> findByCaregiverId(Long caregiverId);
}
