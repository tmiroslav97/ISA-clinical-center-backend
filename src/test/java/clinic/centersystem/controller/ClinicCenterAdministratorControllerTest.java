package clinic.centersystem.controller;


import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.exception.CCANotPredefinedException;
import clinic.centersystem.exception.UserExistsException;
import clinic.centersystem.exception.UserNotFoundException;
import clinic.centersystem.service.ClinicCenterAdminServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClinicCenterAdministratorControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClinicCenterAdminServiceImpl clinicCenterAdminServiceMock;

    private static final String REG_CA_ENDPOINT_NOT_VALID = "/cca/reg-cca/2";
    private static final Long ADMIN_ID_NOT_VALID = Long.valueOf(2);
    private static final CCARegReqDTO ccaRegReqDTONotValid = new CCARegReqDTO("Mirosalv","Tomic","tomic.miroslav97@gmail.com","123");
    private static final String REG_CA_ENDPOINT_VALID = "/cca/reg-cca/1";
    private static final Long ADMIN_ID_VALID = Long.valueOf(1);
    private static final CCARegReqDTO ccaRegReqDTOValid = new CCARegReqDTO("Mirosalv","Tomic","tomic@gmail.com","123");


    @Test
    public void registerCCAShouldReturnNotFoundWhenUserNotFoundExceptionIsThrown(){
        when(clinicCenterAdminServiceMock.registerCCA(ccaRegReqDTONotValid, ADMIN_ID_NOT_VALID)).thenThrow(UserNotFoundException.class);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REG_CA_ENDPOINT_NOT_VALID, ccaRegReqDTONotValid, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is NOT_FOUND.", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void registerCCAShouldReturnBadRequestWhenCCANotPredefinedExceptionIsThrown(){
        when(clinicCenterAdminServiceMock.registerCCA(ccaRegReqDTOValid, ADMIN_ID_VALID)).thenThrow(CCANotPredefinedException.class);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REG_CA_ENDPOINT_VALID, ccaRegReqDTOValid, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void registerCCAShouldReturnBadRequestWhenUserExistsExceptionIsThrown(){
        when(clinicCenterAdminServiceMock.registerCCA(ccaRegReqDTOValid, ADMIN_ID_VALID)).thenThrow(UserExistsException.class);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REG_CA_ENDPOINT_VALID, ccaRegReqDTONotValid, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void registerCCAShouldReturnOkAndMessageWhenRegisterIsSuccessful(){
        when(clinicCenterAdminServiceMock.registerCCA(ccaRegReqDTOValid, ADMIN_ID_VALID)).thenReturn("Successfully added new clinic center administrator");

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REG_CA_ENDPOINT_VALID, ccaRegReqDTOValid, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is OK.", HttpStatus.OK, responseEntity.getStatusCode());
        assertNull("Response body is successfully added new clinic center administrator.", success);
    }
}
