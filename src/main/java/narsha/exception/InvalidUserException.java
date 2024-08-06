package narsha.exception;

import org.springframework.http.HttpStatus;

public class InvalidUserException extends RuntimeException {
    private final HttpStatus status;

    public InvalidUserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
