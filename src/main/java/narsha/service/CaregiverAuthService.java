package narsha.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import narsha.dto.caregiver.CaregiverLoginRequest;
import narsha.dto.caregiver.CaregiverRegisterRequest;
import narsha.entity.Caregiver;
import narsha.repository.CaregiverRepository;

@Service
public class CaregiverAuthService extends AuthService<Caregiver, CaregiverRegisterRequest, CaregiverLoginRequest> {

    public CaregiverAuthService(CaregiverRepository caregiverRepository, ImageUploadService imageUploadService) {
        super(caregiverRepository, imageUploadService);
    }

    public void createCaregiver(CaregiverRegisterRequest request, MultipartFile profileImage, BindingResult bindingResult) {
        createUser(request, profileImage, bindingResult);
    }

    public void loginCaregiver(CaregiverLoginRequest request, BindingResult bindingResult, HttpSession session) {
        loginUser(request, bindingResult, session);
    }
}
