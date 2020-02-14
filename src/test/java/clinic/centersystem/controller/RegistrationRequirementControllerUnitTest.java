package clinic.centersystem.controller;


import clinic.centersystem.authentication.JwtAuthenticationRequest;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.exception.RegistrationRequirementNotFoundException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.service.RegistrationRequirementServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationRequirementControllerUnitTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private RegistrationRequirementServiceImpl registrationRequirementServiceMock;

    private String accessToken;
    private static final String APPROVE_ENDPOINT_USER_EXISTS = "/reg/approve/4";
    private static final String REJECT_ENDPOINT_USER_EXISTS = "/reg/reject/4/Not enough information";
    private static final String APPROVE_EDNPOINT_REG_REQ_NOT_EXISTS = "/reg/approve/50";
    private static final String REJECT_EDNPOINT_REG_REQ_NOT_EXISTS = "/reg/reject/50/Not enough information";
    private static final String APPROVE_ENDPOINT_USER_NOT_EXISTS = "/reg/approve/1";
    private static final String REJECT_ENDPOINT_USER_NOT_EXISTS = "/reg/reject/1/Not enough information";
    private static final String MESSAGE = "Not enough information";
    private static final Long VALID_ID = 1L;
    private static final Long USER_EXISTS_ID = 4L;
    private static final Long NOT_VALID_ID = 50L;

    @Before
    public void login() {
        ResponseEntity<LoginUserResponse> responseEntity = testRestTemplate.postForEntity("/sec/login", new JwtAuthenticationRequest("tomic.miroslav97@gmail.com", "12345"), LoginUserResponse.class);
        accessToken = "Bearer " + responseEntity.getBody().getToken();
    }

    @Test
    public void rejectRegistrationRequestShouldReturnNotFoundWhenRegistrationRequirementNotFoundExceptionIsThrown() {
        when(registrationRequirementServiceMock.rejectRegistrationRequest(NOT_VALID_ID, MESSAGE)).thenThrow(RegistrationRequirementNotFoundException.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_EDNPOINT_REG_REQ_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is NOT_FOUND.", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }


    @Test
    public void approveRegistrationRequestShouldReturnNotFoundWhenRegistrationRequirementNotFoundExceptionIsThrown() {
        when(registrationRequirementServiceMock.approveRegistrationRequest(NOT_VALID_ID)).thenThrow(RegistrationRequirementNotFoundException.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_EDNPOINT_REG_REQ_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is NOT_FOUND.", HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void rejectRegistrationRequestShouldReturnBadRequestWhenResourceExistsExceptionIsThrown() {
        when(registrationRequirementServiceMock.rejectRegistrationRequest(USER_EXISTS_ID, MESSAGE)).thenThrow(ResourceExistsException.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_ENDPOINT_USER_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void approveRegistrationRequestShouldReturnBadRequestWhenResourceExistsExceptionIsThrown() {
        when(registrationRequirementServiceMock.approveRegistrationRequest(USER_EXISTS_ID)).thenThrow(ResourceExistsException.class);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_ENDPOINT_USER_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNull("Response does not contain body.", success);
    }

    @Test
    public void approveRegistrationRequestShouldReturnOKAndSuccMsgWhenRegistrationRequirementIsApproved() {
        when(registrationRequirementServiceMock.approveRegistrationRequest(VALID_ID)).thenReturn("Patient registration approved");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(APPROVE_ENDPOINT_USER_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is OK.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Patient registration approved", success);
    }

    @Test
    public void rejectRegistrationRequestShouldReturnOKAndSuccMsgWhenRegistrationRequirementIsRejected() {
        when(registrationRequirementServiceMock.rejectRegistrationRequest(VALID_ID, MESSAGE)).thenReturn(2);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);

        HttpEntity<Object> request = new HttpEntity<>(null, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_ENDPOINT_USER_NOT_EXISTS, request, String.class);

        String success = responseEntity.getBody();
        assertEquals("Http status is OK.", HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Patient registration rejected", success);
    }

    //White space in url is problem doesn't work
//    @Test
//    public void rejectRegistrationRequestShouldReturnBadRequestAndErrMsgWhenRegistrationRequirementMissMesage() {
//        when(registrationRequirementServiceMock.rejectRegistrationRequest(VALID_ID, MESSAGE_EMPTY)).thenReturn(1);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", accessToken);
//
//        HttpEntity<Object> request = new HttpEntity<>(null, headers);
//
//        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(REJECT_ENDPOINT_MESSAGE_MISSING, request, String.class);
//
//        String success = responseEntity.getBody();
//        assertEquals("Http status is BAD_REQUEST.", HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Missing message", success);
//    }

}
