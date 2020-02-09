package clinic.centersystem.exception;

public class RegistrationRequirementNotFoundException extends RuntimeException {
    public RegistrationRequirementNotFoundException(String errorMsg){
        super((errorMsg));
    }
}
