package narsha.service;

import jakarta.servlet.http.HttpSession;
import narsha.dto.LoginRequest;
import narsha.dto.LoginResponse;
import narsha.dto.RegisterRequest;
import narsha.entity.User;
import narsha.exception.InvalidLoginException;
import narsha.exception.InvalidRegisterException;
import narsha.exception.UnauthenticatedUserException;
import narsha.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Objects;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(RegisterRequest request, BindingResult bindingResult) {
        validateBindingResult(bindingResult);
        DuplicateRegistration(request);
        request.passwordMatch();

        User user = request.toEntity();
        userRepository.save(user);
    }

    public void loginUser(LoginRequest request, BindingResult bindingResult, HttpSession session) {
        validateBindingResult(bindingResult);
        User user = verifyCredentials(request);

        session.setAttribute("user", user);
    }



    public void isAuthenticated(HttpSession session, long id) {
        if (!Objects.equals(((User) (session.getAttribute("user"))).getId(), id)) {
            throw new UnauthenticatedUserException("User is not authenticated.");
        }
    }

    // 세션 사용자 확인 및 가져오기 메서드
    public User getUserFromSession(HttpSession session) {
        Object user = session.getAttribute("user");
        if (!(user instanceof User)) {
            throw new UnauthenticatedUserException("User is not authenticated.");
        }
        return (User) user;
    }

    public LoginResponse getDtoFromSession(HttpSession session) {
        Object user = session.getAttribute("user");
        if (!(user instanceof User)) {
            throw new UnauthenticatedUserException("User is not authenticated.");
        }
        return new LoginResponse(((User) user).getEmail(), ((User) user).getPassword());
    }



    private void validateBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "Unknown error";
            throw new InvalidRegisterException(errorMessage);
        }
    }

    private void DuplicateRegistration(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidRegisterException("You are already a registered user.");
        }
    }

    private User verifyCredentials(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new InvalidLoginException("This is not a registered email.");
        }
        request.passwordMatch(user);
        return user;
    }
}
