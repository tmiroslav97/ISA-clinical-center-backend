package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicCenterAdminConverter;
import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.exception.CCANotPredefinedException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.exception.UserNotFoundException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.ClinicCenterAdminRepository;
import clinic.centersystem.service.intf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicCenterAdminServiceImpl implements ClinicCenterAdminService {

    @Autowired
    private ClinicCenterAdminRepository clinicCenterAdminRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private static final Logger logger = LoggerFactory.getLogger(ClinicCenterAdminServiceImpl.class);

    @Override
    public ClinicCenterAdmin findById(Long id) {
        return this.clinicCenterAdminRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User not found"));
    }

    @Override
    public List<ClinicCenterAdmin> findAll() {
        return null;
    }

    @Override
    public ClinicCenterAdmin save(CCARegReqDTO ccaRegReqDTO) {
        ClinicCenterAdmin clinicCenterAdmin = ClinicCenterAdminConverter.toCreateClinicCenterAdmin(ccaRegReqDTO);
        List<Authority> auths = this.authorityService.findByName("ROLE_CCADMIN");
        clinicCenterAdmin.setAuthorities(auths);
        clinicCenterAdmin = this.clinicCenterAdminRepository.save(clinicCenterAdmin);
        return clinicCenterAdmin;
    }

    @Override
    public ClinicCenterAdminResponse clinicCenterAdmin(Long id) {
        ClinicCenterAdmin clinicCenterAdmin = this.findById(id);
        return ClinicCenterAdminConverter.toCreateClinicCenterAdminResponse(clinicCenterAdmin);
    }

    @Override
    public String registerCCA(CCARegReqDTO ccaRegReqDTO, Long id) {
        ClinicCenterAdmin clinicCenterAdmin = this.findById(id);
        if (!clinicCenterAdmin.isPredefined()) {
            throw new CCANotPredefinedException();
        }

        if (userService.existsByEmail(ccaRegReqDTO.getEmail())) {
            throw new ResourceExistsException("User with email " + ccaRegReqDTO.getEmail() + " already exists.");
        }

        String password = ccaRegReqDTO.getPassword();
        ccaRegReqDTO.setPassword(this.passwordEncoder.encode(ccaRegReqDTO.getPassword()));
        ClinicCenterAdmin newCCAdmin = this.save(ccaRegReqDTO);
        String subject = "Account registration";
        String answer = String.format(
                "Your are registered as clinic center administrator" +
                        "\nYour email(username) is: " + newCCAdmin.getEmail() +
                        "\nYou password is: " + password);

        emailService.sendMailTo(newCCAdmin.getEmail(), subject, answer);

        String msg = "Successfully added new clinic center administrator";
        return msg;
    }


}
