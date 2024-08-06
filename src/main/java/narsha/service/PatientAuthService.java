package narsha.service;

import jakarta.servlet.http.HttpSession;
import narsha.dto.patient.PatientLoginRequest;
import narsha.dto.patient.PatientRegisterRequest;
import narsha.entity.Patient;
import narsha.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PatientAuthService extends AuthService<Patient, PatientRegisterRequest, PatientLoginRequest> {

    public PatientAuthService(PatientRepository patientRepository, ImageUploadService imageUploadService) {
        super(patientRepository, imageUploadService);
    }

    public void createPatient(PatientRegisterRequest request, MultipartFile profileImage, BindingResult bindingResult) {
        createUser(request, profileImage, bindingResult);
    }

    public void loginPatient(PatientLoginRequest request, BindingResult bindingResult, HttpSession session) {
        loginUser(request, bindingResult, session);
    }
}
