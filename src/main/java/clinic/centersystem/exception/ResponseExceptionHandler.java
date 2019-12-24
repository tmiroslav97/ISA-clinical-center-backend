package clinic.centersystem.exception;

import clinic.centersystem.model.RegistrationRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserExistsException.class})
    protected ResponseEntity<Object> handleUserExistsException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "User already exists";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "User not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {CCANotPredefinedException.class})
    protected ResponseEntity<Object> handleCCANotPredefinedException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Clinic center admin is not predefined admin";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {RegistrationRequirementNotFoundException.class})
    protected ResponseEntity<Object> handleRegistrationRequirementNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Registration requirement not found";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleUserNameNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "User by email can't be found";
        System.out.println(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Service unavailable";
        System.out.println("Exception in app occurred");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }


}
