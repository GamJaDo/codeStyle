package narsha.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import narsha.entity.Caregiver;
import narsha.entity.Patient;
import narsha.exception.InvalidLoginException;

@Service
public class LoginService {

	public void checkLoginStatus(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user instanceof Patient) {
            return;
        }
        if (user instanceof Caregiver) {
            return;
        }
        throw new InvalidLoginException("No user is logged in");
    }
	
	public void logout(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user instanceof Patient || user instanceof Caregiver) {
            session.invalidate();
            return;
        }
        throw new InvalidLoginException("No user is logged in to logout");
    }
}
