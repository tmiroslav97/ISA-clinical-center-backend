package clinic.centersystem.controller;


import clinic.centersystem.authentication.JwtAuthenticationRequest;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.exception.RegistrationRequirementNotFoundException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.service.intf.RegistrationRequirementService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegistrationRequirementControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RegistrationRequirementService registrationRequirementService;

    private String accessToken;
    private static final String APPROVE_ENDPOINT_USER_EXISTS = "/reg/approve/4";
    private static final String REJECT_ENDPOINT_USER_EXISTS = "/reg/reject/4/Not enough information";
    private static final String APPROVE_EDNPOINT_REG_REQ_NOT_EXISTS = "/reg/approve/50";
    private static final String REJECT_EDNPOINT_REG_REQ_NOT_EXISTS = "/reg/reject/50/Not enough information";
    private static final String APPROVE_ENDPOINT_USER_NOT_EXISTS = "/reg/approve/1";
    private static final String REJECT_ENDPOINT_USER_NOT_EXISTS = "/reg/reject/2/Not enough information";
    private static final String REJECT_ENDPOINT_MESSAGE_MISSING = "/reg/reject/1/ ";
    private static final String MESSAGE = "Not enough information";
    private static final String MESSAGE_EMPTY = "";
    private static final Long VALID_ID = 1L;
    private static final Long USER_EXISTS_ID = 4L;
    private static final Long NOT_VALID_ID = 50L;

    @Before
    public void login() {
        ResponseEntity<LoginUserResponse> responseEntity = testRestTemplate.postForEntity("/sec/login", new JwtAuthenticationRequest("tomic.miroslav97@gmail.com", "123"), LoginUserResponse.class);
        accessToken = "Bearer " + responseEntity.getBody().getToken();
    }

    @Test(expected = RegistrationRequirementNotFoundException.class)
    public void rejectRegistrationRequestShouldReturnNotFoundWhenRegistrationRequirementNotFoundExceptionIsThrown() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_EDNPOINT_REG_REQ_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is NOT_FOUND.", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Registration requirement not found", success);

        registrationRequirementService.rejectRegistrationRequest(NOT_VALID_ID, MESSAGE);
    }


    @Test(expected = RegistrationRequirementNotFoundException.class)
    public void approveRegistrationRequestShouldReturnNotFoundWhenRegistrationRequirementNotFoundExceptionIsThrown() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_EDNPOINT_REG_REQ_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is NOT_FOUND.", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Registration requirement not found", success);

        registrationRequirementService.approveRegistrationRequest(NOT_VALID_ID);
    }

    @Test(expected = ResourceExistsException.class)
    public void approveRegistrationRequestShouldReturnBadRequestWhenResourceExistsExceptionIsThrown() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_ENDPOINT_USER_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User with email tomic.miroslav97@gmail.com already exists", success);

        registrationRequirementService.approveRegistrationRequest(USER_EXISTS_ID);
    }

    @Test(expected = ResourceExistsException.class)
    public void rejectRegistrationRequestShouldReturnBadRequestWhenResourceExistsExceptionIsThrown() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_ENDPOINT_USER_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User with email tomic.miroslav97@gmail.com already exists", success);

        registrationRequirementService.rejectRegistrationRequest(USER_EXISTS_ID, MESSAGE);
    }

    @Test
    public void approveRegistrationRequestShouldReturnOKAndSuccMsgWhenRegistrationRequirementIsApproved() {
        int size = registrationRequirementService.findAll().size();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_ENDPOINT_USER_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is OK.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Patient registration approved", success);
        assertEquals(size-1, registrationRequirementService.findAll().size());
    }

    @Test
    public void rejectRegistrationRequestShouldReturnOKAndSuccMsgWhenRegistrationRequirementIsRejected() {
        int size = registrationRequirementService.findAll().size();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_ENDPOINT_USER_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is OK.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Patient registration rejected", success);

        assertEquals(size-1, registrationRequirementService.findAll().size());
    }

}
