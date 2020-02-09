package clinic.centersystem.service;

import clinic.centersystem.exception.RegistrationRequirementNotFoundException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.repository.RegistrationRequirementRepository;
import clinic.centersystem.service.intf.RegistrationRequirementService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class RegistrationRequirementServiceIntegrationTest {

    @Autowired
    private RegistrationRequirementService registrationRequirementService;

    @Autowired
    private RegistrationRequirementRepository registrationRequirementRepository;

    private static final Long REG_REQ_NOT_VALID = 15L;
    private static final String MESSAGE = "Not enough information";
    private static final Long REG_REQ_ID_VALID = 4L;



    @Test(expected = ResourceExistsException.class)
    @Order(1)
    public void approveRegistrationRequestShouldThrowResourceExistsExceptionWhenUserExists() {
        registrationRequirementService.approveRegistrationRequest(REG_REQ_ID_VALID);

    }

    @Test(expected = ResourceExistsException.class)
    @Order(2)
    public void rejectRegistrationRequestShouldThrowResourceExistsExceptionWhenUserExists() {
        registrationRequirementService.rejectRegistrationRequest(REG_REQ_ID_VALID, MESSAGE);
    }


    @Test(expected = RegistrationRequirementNotFoundException.class)
    @Order(3)
    public void rejectRegistrationRequestShouldThrowRegistrationRequirementNotFoundExceptionWhenRegistrationRequirementNotExists() {
        registrationRequirementService.rejectRegistrationRequest(REG_REQ_NOT_VALID, MESSAGE);
    }

    @Test(expected = RegistrationRequirementNotFoundException.class)
    @Order(4)
    public void approveRegistrationRequestShouldThrowRegistrationRequirementNotFoundExceptionWhenRegistrationRequirementNotExists() {
        registrationRequirementService.approveRegistrationRequest(REG_REQ_NOT_VALID);
    }

    @Test
    @Order(5)
    public void approveRegistrationRequestShouldReturnSuccMsg() {
        int size = registrationRequirementRepository.findAll().size();

        String succcMsg = registrationRequirementService.approveRegistrationRequest(5L);
        assertEquals("Patient registration approved", succcMsg);

        assertEquals(size - 1, registrationRequirementRepository.findAll().size());
    }

    @Test
    @Order(6)
    public void rejectRegistrationRequestShouldReturnErrorMsg() {
        int flag = registrationRequirementService.rejectRegistrationRequest(6L, "");
        assertEquals(1, flag);
    }

    @Test
    @Order(7)
    public void rejectRegistrationRequestShouldReturnSuccMsg() {
        int size = registrationRequirementRepository.findAll().size();

        int flag = registrationRequirementService.rejectRegistrationRequest(3L, "Rejected");
        assertEquals(2, flag);
        assertEquals(size - 1, registrationRequirementRepository.findAll().size());
    }
}
