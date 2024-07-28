package narsha.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import narsha.dto.LoginRequest;
import narsha.dto.RegisterRequest;
import narsha.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
    private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request, BindingResult bindingResult) {
    	authService.createUser(request, bindingResult);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, BindingResult bindingResult, HttpSession session){
    	authService.loginUser(request, bindingResult, session);
    	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
