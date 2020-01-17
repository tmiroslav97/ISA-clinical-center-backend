package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicCenterAdminConverter;
import clinic.centersystem.converter.ClinicConverter;
import clinic.centersystem.converter.RegistrationRequirementConverter;
import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.request.ClinicRequestDTO;
import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.RegistrationRequirementResponse;
import clinic.centersystem.exception.CCANotPredefinedException;
import clinic.centersystem.exception.UserExistsException;
import clinic.centersystem.exception.UserNotFoundException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.ClinicCenterAdminRepository;
import clinic.centersystem.service.intf.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicCenterAdminServiceImpl implements ClinicCenterAdminService {

    @Autowired
    private ClinicCenterAdminRepository clinicCenterAdminRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RegistrationRequirementService registrationRequirementService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    private static final Logger logger = LoggerFactory.getLogger(ClinicCenterAdminServiceImpl.class);

    @Override
    public ClinicCenterAdmin findById(Long id) {
        return this.clinicCenterAdminRepository.findById(id).orElseThrow(UserNotFoundException::new);
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
    public List<RegistrationRequirementResponse> registrationRequirementList() {
        return registrationRequirementService.findAll().stream().map(RegistrationRequirementConverter::toCreateRegistrationRequirementResponse).collect(Collectors.toList());
    }

    @Override
    public String approveRegistrationRequest(Long id) {
        RegistrationRequirement req = registrationRequirementService.findById(id);
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        if (this.userService.existsByEmail(req.getEmail())) {
            throw new UserExistsException();
        }
        Patient patient = this.patientService.save(req);
        this.registrationRequirementService.deleteById(id);
        String subject = "Account registration";
        String answer = String.format(
                "    Patient account was create successfully!\n" +
                        "    Please follow this link to activate account:\n" +
                        "    http://localhost:8080/cca/activate-account/%s"
                , patient.getId().toString());

        emailService.sendMailTo(patient.getEmail(), subject, answer);

        return "Patient registration approved";
    }

    @Override
    public String rejectRegistrationRequest(Long id, String message) {
        RegistrationRequirement req = this.registrationRequirementService.findById(id);
        String subject = "Account registration";
        this.registrationRequirementService.deleteById(id);

        emailService.sendMailTo(req.getEmail(), subject, message);

        return "Patient registration rejected";
    }

    @Override
    public String registerCCA(CCARegReqDTO ccaRegReqDTO, Long id) {
        ClinicCenterAdmin clinicCenterAdmin = this.findById(id);
        if (!clinicCenterAdmin.isPredefined()) {
            throw new CCANotPredefinedException();
        }
        User user = this.userService.findByUsername(ccaRegReqDTO.getEmail());
        if (user != null) {
            throw new UserExistsException();
        }
        ccaRegReqDTO.setPassword(this.passwordEncoder.encode(ccaRegReqDTO.getPassword()));
        ClinicCenterAdmin newCCAdmin = this.save(ccaRegReqDTO);
        String msg = "Successfully added new clinic center administrator";
        return msg;
    }

    @Override
    public String activateAccount(Long id, HttpServletResponse httpServletResponse) {
        Patient patient = this.patientService.findById(id);
        patient.setActivated(true);
        patient = this.patientService.save(patient);

        httpServletResponse.setHeader("Location", "http://localhost:3000/login");
        return "Account is activated!";
    }

    @Override
    public boolean registerClinic(ClinicRequestDTO clinicRequestDTO) {
        if (this.clinicService.existsByName(clinicRequestDTO.getName())) {
            return false;
        }

        Clinic clinic = this.clinicService.save(clinicRequestDTO);

        return true;
    }

    @Override
    public List<ClinicResponse> getClinics() {
        List<Clinic> clinics = this.clinicService.findAll();
        List<ClinicResponse> clinicResponses = new ArrayList<ClinicResponse>();
        for (Clinic clinic : clinics) {
            clinicResponses.add(ClinicConverter.toCreateClinicResponseFromClinic(clinic));
        }
        return clinicResponses;
    }

    @Override
    public String registerClinicAdmin(ClinicAdminReqDTO clinicAdminReqDTO) {
        clinicAdminReqDTO.setPassword(this.passwordEncoder.encode(clinicAdminReqDTO.getPassword()));
        if (this.userService.existsByEmail(clinicAdminReqDTO.getEmail())) {
            throw new UserExistsException();
        }
        ClinicAdmin clinicAdmin = this.clinicAdminService.save(clinicAdminReqDTO);

        Clinic clinic = this.clinicService.findById(clinicAdminReqDTO.getClinicId());

        clinic.getClinicAdmins().add(clinicAdmin);
        clinicAdmin.setClinic(clinic);

        clinicAdmin = this.clinicAdminService.saveClinicAdmin(clinicAdmin);
        clinic = this.clinicService.saveClinic(clinic);

        return "Clinic admin successfully added";
    }
}
