package clinic.centersystem.exception;

public class ResourceNotExistsException extends RuntimeException {
    public ResourceNotExistsException(String errorMessage) {
        super(errorMessage);
    }
}
