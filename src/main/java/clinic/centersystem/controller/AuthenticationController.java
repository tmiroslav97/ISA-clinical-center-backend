package clinic.centersystem.controller;

import clinic.centersystem.authentication.JwtAuthenticationRequest;
import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.dto.request.PasswordChangerRequestDTO;
import clinic.centersystem.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/sec", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginUserResponse> createAuthenticationToken(HttpServletRequest servletRequest, @RequestBody JwtAuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(authenticationService.login(servletRequest, authenticationRequest), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody RegistrationRequirementDTO userRequest) {
        boolean flag = authenticationService.register(userRequest);
        if (flag) {
            return new ResponseEntity<>("Successfully registered, wait for confirmation email!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Account with this email already exists!", HttpStatus.CREATED);
        }

    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangerRequestDTO passwordChangerRequestDTO) {
        LoginUserResponse loginUserResponse = authenticationService.changePassword(passwordChangerRequestDTO);
        if (loginUserResponse != null) {
            return new ResponseEntity<>(loginUserResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Password couldn't be changed!", HttpStatus.OK);
        }
    }

}
