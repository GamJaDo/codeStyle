package narsha.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import narsha.dto.UserImageUpdateRequest;
import narsha.dto.UserNameUpdateRequest;
import narsha.dto.UserPasswordUpdateRequest;
import narsha.dto.caregiver.CaregiverInfoResponse;
import narsha.dto.caregiver.CaregiverProfileUpdateRequest;
import narsha.entity.Caregiver;
import narsha.repository.CaregiverRepository;

@Service
public class CaregiverService extends UserService<Caregiver> {

    public CaregiverService(CaregiverRepository caregiverRepository, ImageUploadService imageUploadService,
    		CareEnrollmentService careEnrollmentService) {
        super(caregiverRepository, imageUploadService, careEnrollmentService);
    }

    public CaregiverInfoResponse getCaregiverInfo(HttpSession session) {
        Caregiver caregiver = getSessionUser(session);
        return caregiver.toDto();
    }
    
    public void caregiverImageUpdate(UserImageUpdateRequest request, HttpSession session) {
        updateImage(request, session);
    }

    public void caregiverNameUpdate(UserNameUpdateRequest request, HttpSession session) {
        updateName(request, session);
    }

    public void caregiverPasswordUpdate(UserPasswordUpdateRequest request, HttpSession session) {
        updatePassword(request, session);
    }

    public void caregiverProfileUpdate(CaregiverProfileUpdateRequest request, HttpSession session) {
        updateProfile(request, session);
    }
}