package narsha.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import narsha.dto.LoginRequest;
import narsha.dto.RegisterRequest;
import narsha.entity.User;
import narsha.exception.InvalidLoginException;
import narsha.exception.InvalidRegisterException;
import narsha.repository.UserRepository;

@Service
public class AuthService {
	
	private UserRepository userRepository;
	
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void createUser(RegisterRequest request, BindingResult bindingResult){
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
	
	private void validateBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult
					.getFieldError()
					.getDefaultMessage();
			throw new InvalidRegisterException(errorMessage);
		}
	}
	
	public void DuplicateRegistration(RegisterRequest request) {
		if (!userRepository.existsByEmail(request.getEmail())) {
			throw new InvalidRegisterException("You are already a registered user.");
		}
	}
	
	public User verifyCredentials(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail());
		if (user == null) {
			throw new InvalidLoginException("This is not a registered email.");
		}
		request.passwordMatch(user);
		return user;
	}
}
