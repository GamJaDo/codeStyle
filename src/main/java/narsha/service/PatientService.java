package narsha.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;
import narsha.dto.UserImageUpdateRequest;
import narsha.dto.UserNameUpdateRequest;
import narsha.dto.UserPasswordUpdateRequest;
import narsha.dto.patient.PatientInfoResponse;
import narsha.dto.patient.PatientProfileUpdateRequest;
import narsha.entity.Patient;
import narsha.repository.PatientRepository;

@Service
public class PatientService extends UserService<Patient> {

    public PatientService(PatientRepository patientRepository, ImageUploadService imageUploadService) {
        super(patientRepository, imageUploadService);
    }

    public PatientInfoResponse getPatientInfo(HttpSession session) {
        String patientAccount = getSessionUserAccount(session);
        Patient patient = findUserByAccount(patientAccount);
        return patient.toDto();
    }

    public void patientImageUpdate(UserImageUpdateRequest request, HttpSession session) {
        updateImage(request, session);
    }

    public void patientNameUpdate(UserNameUpdateRequest request, HttpSession session) {
        updateName(request, session);
    }

    public void patientPasswordUpdate(UserPasswordUpdateRequest request, HttpSession session) {
        updatePassword(request, session);
    }
    
    public void patientProfileUpdate(PatientProfileUpdateRequest request, HttpSession session) {
    	updateProfile(request, session);
    }
}
