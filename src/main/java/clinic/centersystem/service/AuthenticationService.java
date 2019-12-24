package clinic.centersystem.service;

import clinic.centersystem.authentication.JwtAuthenticationRequest;
import clinic.centersystem.converter.UserConverter;
import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.LoginUserResponse;
import clinic.centersystem.dto.request.PasswordChangerRequestDTO;
import clinic.centersystem.model.RegistrationRequirement;
import clinic.centersystem.model.User;
import clinic.centersystem.security.TokenUtils;
import clinic.centersystem.service.intf.PatientService;
import clinic.centersystem.service.intf.RegistrationRequirementService;
import clinic.centersystem.service.intf.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private RegistrationRequirementService registrationRequirementService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginUserResponse login(HttpServletRequest request, JwtAuthenticationRequest jwtAuthenticationRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(jwtAuthenticationRequest.getUsername(),
                        jwtAuthenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());

        LoginUserResponse loginUserResponse = UserConverter.toCreateUserLoginResponse(user, jwt);
        return loginUserResponse;
    }

    public boolean register(RegistrationRequirementDTO userRequest) {
        User existUser = this.userService.findByUsername(userRequest.getEmail());
        if (existUser != null) {
            return false;
        }
        if (!userRequest.getPassword().equals(userRequest.getPassword2())) {
            return false;
        }
        userRequest.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        RegistrationRequirement registrationRequirement = this.registrationRequirementService.save(userRequest);
        //samo proba da vidimo da li cuva i usera i pacijenta automatski i naravno cuva
        //Patient patient = this.patientService.save(userRequest);
        return true;
    }

    public LoginUserResponse changePassword(PasswordChangerRequestDTO passwordChangerRequestDTO) {
        User user = this.customUserDetailsService.changePassword(passwordChangerRequestDTO.getOldPassword(), passwordChangerRequestDTO.getNewPassword());
        String jwt = tokenUtils.generateToken(user.getEmail());

        LoginUserResponse loginUserResponse = UserConverter.toCreateUserLoginResponse(user, jwt);

        return loginUserResponse;
    }

}
