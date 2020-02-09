package clinic.centersystem.service;

import clinic.centersystem.dto.request.MailRequestDTO;
import clinic.centersystem.exception.RegistrationRequirementNotFoundException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.RegistrationRequirementRepository;
import clinic.centersystem.service.intf.RegistrationRequirementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationRequirementServiceUnitTest {

    @MockBean
    private RegistrationRequirementRepository registrationRequirementRepositoryMock;

    @MockBean
    private UserServiceImpl userServiceMock;

    @MockBean
    private PatientServiceImpl patientServiceMock;

    @MockBean
    private PasswordEncoder passwordEncoderMock;

//    @MockBean
//    private EmailService emailServiceMock;

//    @MockBean
//    private ApplicationEventPublisher applicationEventPublisherMock;

    @Autowired
    private RegistrationRequirementService registrationRequirementService;

    private static final String USER_EXISTS_EMAIL = "tomic.miroslav97@gmail.com";
    private static final Long REG_REQ_NOT_VALID = 15L;
    private static final String MESSAGE = "Not enough information";
    private static final Long REG_REQ_ID_VALID = 4L;
    private static final String ADDRESS_VALID = "Safarikova 31";
    private static final String CITY_VALID = "Bijeljina";
    private static final String COUNTRY_VALID = "Bosna";
    private static final String EMAIL_VALID = "tomic.miroslav97@gmail.com";
    private static final String FIRST_NAME_VALID = "Miki";
    private static final String LAST_NAME_VALID = "Peric";
    private static final String PASSWORD_VALID = "123";
    private static final String PASSWORD2_VALID = "123";
    private static final String PHONE_NUME_VALID = "065987654";
    private static final String UNOIP_VALID = "1234543";
    private static final Long VERSION_VALID = 0L;

    @Test
    public void rejectRegistrationRequestShouldReturnErrorMsg() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(1L, FIRST_NAME_VALID, LAST_NAME_VALID,
                "miroslav.tomic@outlook.com", PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        when(registrationRequirementRepositoryMock.findById(1L)).thenReturn(Optional.of(registrationRequirement));
        when(userServiceMock.existsByEmail(USER_EXISTS_EMAIL)).thenReturn(false);

        int flag = registrationRequirementService.rejectRegistrationRequest(1L, "");
        assertEquals(1, flag);
    }

    @Test
    public void rejectRegistrationRequestShouldReturnSuccMsg() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(1L, FIRST_NAME_VALID, LAST_NAME_VALID,
                "miroslav.tomic@outlook.com", PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        when(registrationRequirementRepositoryMock.findById(1L)).thenReturn(Optional.of(registrationRequirement));
        when(userServiceMock.existsByEmail(USER_EXISTS_EMAIL)).thenReturn(false);

        int flag = registrationRequirementService.rejectRegistrationRequest(1L, "Rejected");
        //verify(applicationEventPublisherMock, times(1)).publishEvent(new MailRequestDTO("miroslav.tomic@outlook.com", "Account registration",
                //"Rejected"));
        verify(registrationRequirementRepositoryMock, times(1)).deleteById(1L);
        assertEquals(2, flag);
    }

    @Test
    public void approveRegistrationRequestShouldReturnSuccMsg() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(1L, FIRST_NAME_VALID, LAST_NAME_VALID,
                "miroslav.tomic@outlook.com", PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        Authority authority = new Authority(5L, "ROLE_PATIENT");
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        Patient patient = new Patient(1L, "miroslav.tomic@outlook.com", "123", FIRST_NAME_VALID, LAST_NAME_VALID,
                true, true, null,
                authorities, ADDRESS_VALID, COUNTRY_VALID, CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID,
                new HashSet<>(), new MedicalRecord(), new HashSet<>(), new HashSet<>(),
                new HashSet<>(), false, 0L);
        when(registrationRequirementRepositoryMock.findById(1L)).thenReturn(Optional.of(registrationRequirement));
        when(userServiceMock.existsByEmail(USER_EXISTS_EMAIL)).thenReturn(false);
        when(patientServiceMock.save(registrationRequirement)).thenReturn(patient);

        String succcMsg = registrationRequirementService.approveRegistrationRequest(1L);
        verify(passwordEncoderMock, times(1)).encode(PASSWORD_VALID);
//        verify(applicationEventPublisherMock, times(1)).publishEvent(new MailRequestDTO("miroslav.tomic@outlook.com", "Account registration",
//                "    Patient account created successfully!\n" +
//                        "    Please follow this link to activate account:\n" +
//                        "    http://localhost:8080/sec/activate-account/1"));
        assertEquals("Patient registration approved", succcMsg);
    }

    @Test(expected = ResourceExistsException.class)
    public void approveRegistrationRequestShouldThrowResourceExistsExceptionWhenUserExists() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(REG_REQ_ID_VALID, FIRST_NAME_VALID, LAST_NAME_VALID,
                EMAIL_VALID, PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        when(registrationRequirementRepositoryMock.findById(REG_REQ_ID_VALID)).thenReturn(Optional.of(registrationRequirement));
        when(userServiceMock.existsByEmail(USER_EXISTS_EMAIL)).thenReturn(true);

        registrationRequirementService.approveRegistrationRequest(REG_REQ_ID_VALID);
        verify(registrationRequirementRepositoryMock, times(1)).deleteById(REG_REQ_ID_VALID);
        verify(passwordEncoderMock, times(1)).encode(registrationRequirement.getPassword());
    }

    @Test(expected = ResourceExistsException.class)
    public void rejectRegistrationRequestShouldThrowResourceExistsExceptionWhenUserExists() {
        RegistrationRequirement registrationRequirement = new RegistrationRequirement(REG_REQ_ID_VALID, FIRST_NAME_VALID, LAST_NAME_VALID,
                EMAIL_VALID, PASSWORD_VALID, PASSWORD2_VALID, ADDRESS_VALID, COUNTRY_VALID,
                CITY_VALID, PHONE_NUME_VALID, UNOIP_VALID, VERSION_VALID);
        when(registrationRequirementRepositoryMock.findById(REG_REQ_ID_VALID)).thenReturn(Optional.of(registrationRequirement));
        when(userServiceMock.existsByEmail(USER_EXISTS_EMAIL)).thenReturn(true);

        registrationRequirementService.rejectRegistrationRequest(REG_REQ_ID_VALID, MESSAGE);
        verify(registrationRequirementRepositoryMock, times(1)).deleteById(REG_REQ_ID_VALID);
    }

    @Test(expected = RegistrationRequirementNotFoundException.class)
    public void rejectRegistrationRequestShouldThrowRegistrationRequirementNotFoundExceptionWhenRegistrationRequirementNotExists() {
        when(registrationRequirementRepositoryMock.findById(REG_REQ_NOT_VALID)).thenReturn(Optional.empty());

        registrationRequirementService.rejectRegistrationRequest(REG_REQ_NOT_VALID, MESSAGE);
    }

    @Test(expected = RegistrationRequirementNotFoundException.class)
    public void approveRegistrationRequestShouldThrowRegistrationRequirementNotFoundExceptionWhenRegistrationRequirementNotExists() {
        when(registrationRequirementRepositoryMock.findById(REG_REQ_NOT_VALID)).thenReturn(Optional.empty());

        registrationRequirementService.approveRegistrationRequest(REG_REQ_NOT_VALID);
    }
}
