package narsha.service;

import narsha.dto.CareOfferRequest;
import narsha.dto.CareOfferResponse;
import narsha.entity.CareEnrollment;
import narsha.entity.CareOffer;
import narsha.entity.Caregiver;
import narsha.entity.Patient;
import narsha.repository.CareEnrollmentRepository;
import narsha.repository.CareOfferRepository;
import narsha.repository.CaregiverRepository;
import narsha.repository.PatientRepository;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareEnrollmentService {

    private final CareEnrollmentRepository careEnrollmentRepository;
    private final CareOfferRepository careOfferRepository;
    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;

    public CareEnrollmentService(CareEnrollmentRepository careEnrollmentRepository,
                                 CareOfferRepository careOfferRepository,
                                 CaregiverRepository caregiverRepository,
                                 PatientRepository patientRepository) {
        this.careEnrollmentRepository = careEnrollmentRepository;
        this.careOfferRepository = careOfferRepository;
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
    }

    public void applyToGuin(Long guinId, CareOfferRequest request, HttpSession session) {
        Caregiver caregiver = caregiverRepository.findById(request.getCaregiverId())
                .orElseThrow(() -> new IllegalArgumentException("Caregiver not found"));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        CareOffer careOffer = request.toEntity(caregiver, patient);
        careOfferRepository.save(careOffer);
    }

    public void approveEnrollment(Long enrollmentId, HttpSession session) {
        CareEnrollment enrollment = careEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        enrollment.setApproved(true);
        careEnrollmentRepository.save(enrollment);
    }

    public void rejectEnrollment(Long enrollmentId, HttpSession session) {
        CareEnrollment enrollment = careEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));

        careEnrollmentRepository.delete(enrollment);
    }

    public List<CareOfferResponse> viewReceivedCareOffers(HttpSession session) {
        Long caregiverId = getSessionUserId(session, Caregiver.class);
        List<CareOffer> offers = careOfferRepository.findByCaregiverId(caregiverId);
        return offers.stream().map(CareOffer::toDto).collect(Collectors.toList());
    }

    private <T> Long getSessionUserId(HttpSession session, Class<T> userType) {
        Object user = session.getAttribute("user");
        if (userType.isInstance(user)) {
            if (user instanceof Patient) {
                return ((Patient) user).getId();
            } else if (user instanceof Caregiver) {
                return ((Caregiver) user).getId();
            }
        }
        throw new IllegalArgumentException("Unauthorized access");
    }
}
