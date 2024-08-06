package narsha.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import narsha.dto.AbstractProfileUpdateRequest;
import narsha.dto.UserImageUpdateRequest;
import narsha.dto.UserNameUpdateRequest;
import narsha.dto.UserPasswordUpdateRequest;
import narsha.entity.User;
import narsha.exception.InvalidUserException;
import narsha.repository.UserRepository;

public class UserService<T extends User> {

    protected final UserRepository<T> userRepository;
    private final ImageUploadService imageUploadService;
    protected final CareEnrollmentService careEnrollmentService;

    protected UserService(UserRepository<T> userRepository, ImageUploadService imageUploadService,
    		CareEnrollmentService careEnrollmentService) {
        this.userRepository = userRepository;
        this.imageUploadService = imageUploadService;
        this.careEnrollmentService = careEnrollmentService;
    }

    public T findUserByAccount(String account) {
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new InvalidUserException("User not found.", HttpStatus.NOT_FOUND));
    }

    public void updateImage(UserImageUpdateRequest request, HttpSession session) {
        T user = getSessionUser(session);

        MultipartFile newImage = request.getNewImage();
        if (newImage != null && !newImage.isEmpty()) {
            imageUploadService.profileImageUrlSave(user, newImage);
            userRepository.save(user);
        }
    }

    public void updateName(UserNameUpdateRequest request, HttpSession session) {
        T user = getSessionUser(session);
        user.setName(request.getNewName());
        userRepository.save(user);
    }

    public void updatePassword(UserPasswordUpdateRequest request, HttpSession session) {
        T user = getSessionUser(session);
        request.passwordMatch();
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    public void updateProfile(AbstractProfileUpdateRequest<T> request, HttpSession session) {
        T existingUser = getSessionUser(session);
        T updatedUser = request.toEntity(existingUser);
        userRepository.save(updatedUser);
    }

    protected T getSessionUser(HttpSession session) {
        String account = (String) session.getAttribute("user");
        if (account == null) {
            throw new InvalidUserException("Account not found in session.", HttpStatus.NOT_FOUND);
        }
        return findUserByAccount(account);
    }
}
