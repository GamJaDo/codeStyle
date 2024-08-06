package narsha.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import narsha.entity.Caregiver;
import narsha.entity.Patient;
import narsha.exception.InvalidLoginException;

@Service
public class UrlService {

	public void getControl(HttpSession session, HttpServletResponse response) {
		try {
			Object user = session.getAttribute("user");
		
		    if (user instanceof Patient) {
		    	response.sendRedirect("/api/user/patient/control");
		    } else if (user instanceof Caregiver) {
		    	response.sendRedirect("/api/user/Caregiver/control");
		    }
		} catch (IOException e) {
			throw new InvalidLoginException("You are not logged in.");
		}
	}
	
	public void getProfile(HttpSession session, HttpServletResponse response) {
		try {
			Object user = session.getAttribute("user");
		
		    if (user instanceof Patient) {
		    	response.sendRedirect("/api/user/patient/profile");
		    } else if (user instanceof Caregiver) {
		    	response.sendRedirect("/api/user/Caregiver/profile");
		    }
		} catch (IOException e) {
			throw new InvalidLoginException("You are not logged in.");
		}
	}
}
