package narsha.service;

import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpSession;
import narsha.dto.AbstractRegisterRequest;
import narsha.dto.UserLoginRequest;
import narsha.entity.User;
import narsha.exception.InvalidFormatException;
import narsha.exception.InvalidLoginException;
import narsha.exception.InvalidRegisterException;
import narsha.repository.UserRepository;

public class AuthService<T extends User, R extends AbstractRegisterRequest<T>, L extends UserLoginRequest<T>> {

    protected final UserRepository<T> userRepository;
    private final ImageUploadService imageUploadService;

    protected AuthService(UserRepository<T> userRepository, ImageUploadService imageUploadService) {
        this.userRepository = userRepository;
        this.imageUploadService = imageUploadService;
    }

    public void createUser(R request, MultipartFile profileImage, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        duplicateRegistration(request);
        request.passwordMatch();
        T user = request.toEntity();
        imageUploadService.profileImageUrlSave(user, profileImage);
        userRepository.save(user);
    }

    public void loginUser(L request, BindingResult bindingResult, HttpSession session) {
        validateBindingResult(bindingResult);
        T user = verifyCredentials(request);
        session.setAttribute("user", user);
    }

    private void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldError().getDefaultMessage();
            throw new InvalidFormatException(errorMessage);
        }
    }

    private void duplicateRegistration(R request) {
        if (userRepository.existsByAccount(request.getAccount())) {
            throw new InvalidRegisterException("You are already a registered user.");
        }
    }

    private T verifyCredentials(L request) {
        T user = userRepository.findByAccount(request.getAccount())
                .orElseThrow(() -> new InvalidLoginException("This is not a registered account."));
        request.passwordMatch(user);
        return user;
    }
}
