package narsha.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import narsha.dto.CareEnrollmentRequest;
import narsha.dto.CareEnrollmentResponse;
import narsha.dto.CareOfferRequest;
import narsha.dto.CareOfferResponse;
import narsha.entity.CareEnrollment;
import narsha.entity.CareOffer;
import narsha.entity.Gujik;
import narsha.entity.Patient;
import narsha.entity.Caregiver;
import narsha.repository.CareEnrollmentRepository;
import narsha.repository.CareOfferRepository;
import narsha.repository.GujikRepository;
import narsha.repository.PatientRepository;

@Service
public class CareEnrollmentService {

    private final CareEnrollmentRepository careEnrollmentRepository;
    private final GujikRepository gujikRepository;
    private final PatientRepository patientRepository;
    private final CareOfferRepository careOfferRepository;

    public CareEnrollmentService(CareEnrollmentRepository careEnrollmentRepository,
                                 GujikRepository gujikRepository,
                                 PatientRepository patientRepository,
                                 CareOfferRepository careOfferRepository) {
        this.careEnrollmentRepository = careEnrollmentRepository;
        this.gujikRepository = gujikRepository;
        this.patientRepository = patientRepository;
        this.careOfferRepository = careOfferRepository;
    }

    public void submitEnrollment(CareEnrollmentRequest request, HttpSession session) {
        Long patientId = getSessionUserId(session, Patient.class);
        Gujik gujik = getGujikById(request.getGujikId());
        Patient patient = getPatientById(patientId);
        validateEnrollment(patient, gujik);
        CareEnrollment careEnrollment = request.toEntity(patient, gujik);
        careEnrollmentRepository.save(careEnrollment);
    }

    public List<CareEnrollmentResponse> viewReceivedEnrollments(HttpSession session) {
        Long caregiverId = getSessionUserId(session, Caregiver.class);
        List<CareEnrollment> enrollments = careEnrollmentRepository.findByGujikAuthorId(caregiverId);
        return enrollments.stream().map(CareEnrollment::toDto).collect(Collectors.toList());
    }

    public void approveEnrollment(Long enrollmentId, CareOfferRequest request, HttpSession session) {
        Long caregiverId = getSessionUserId(session, Caregiver.class);
        CareEnrollment enrollment = getCareEnrollmentById(enrollmentId);

        validateCaregiverOwnership(enrollment, caregiverId);

        processEnrollmentApproval(enrollment, request);
    }

    public CareOfferResponse getApprovedCareDetail(HttpSession session) {
        Long patientId = getSessionUserId(session, Patient.class);
        CareOffer offer = getCareOfferByPatientId(patientId);
        return offer.toDto();
    }

    private Gujik getGujikById(Long gujikId) {
        return gujikRepository.findById(gujikId)
                .orElseThrow(() -> new IllegalArgumentException("Gujik post not found"));
    }

    private Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    private CareEnrollment getCareEnrollmentById(Long enrollmentId) {
        return careEnrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found"));
    }

    private CareOffer getCareOfferByPatientId(Long patientId) {
        return careOfferRepository.findByPatientId(patientId)
                .orElseThrow(() -> new IllegalArgumentException("No approved care detail found"));
    }

    private void validateEnrollment(Patient patient, Gujik gujik) {
        if (careEnrollmentRepository.existsByPatientAndGujik(patient, gujik)) {
            throw new IllegalStateException("You have already enrolled for this post.");
        }
    }

    private void validateCaregiverOwnership(CareEnrollment enrollment, Long caregiverId) {
        if (!enrollment.getGujik().getAuthor().getId().equals(caregiverId)) {
            throw new IllegalArgumentException("Unauthorized");
        }
    }

    private void processEnrollmentApproval(CareEnrollment enrollment, CareOfferRequest request) {
        enrollment.setApproved(true);
        careEnrollmentRepository.save(enrollment);
        gujikRepository.delete(enrollment.getGujik());
        CareOffer careOffer = request.toEntity(enrollment.getPatient(), enrollment.getGujik().getAuthor());
        careOfferRepository.save(careOffer);
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
