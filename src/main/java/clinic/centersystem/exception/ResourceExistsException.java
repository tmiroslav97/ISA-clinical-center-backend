package clinic.centersystem.exception;

public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String errorMessage) {
        super(errorMessage);
    }
}
