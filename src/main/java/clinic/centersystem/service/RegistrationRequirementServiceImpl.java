package clinic.centersystem.service;

import clinic.centersystem.converter.RegistrationRequirementConverter;
import clinic.centersystem.dto.request.MailRequestDTO;
import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.RegistrationReqResponseDTO;
import clinic.centersystem.dto.response.RegistrationRequirementResponse;
import clinic.centersystem.exception.RegistrationRequirementNotFoundException;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.model.Patient;
import clinic.centersystem.model.RegistrationRequirement;
import clinic.centersystem.repository.RegistrationRequirementRepository;
import clinic.centersystem.service.intf.PatientService;
import clinic.centersystem.service.intf.RegistrationRequirementService;
import clinic.centersystem.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationRequirementServiceImpl implements RegistrationRequirementService {

    @Autowired
    private RegistrationRequirementRepository registrationRequirementRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public RegistrationRequirement findById(Long id) {
        return this.registrationRequirementRepository.findById(id).orElseThrow(() -> new RegistrationRequirementNotFoundException("Registration requirement not found"));
    }

    @Override
    public List<RegistrationRequirement> findAll() {
        return this.registrationRequirementRepository.findAll();
    }

    @Override
    public RegistrationRequirement save(RegistrationRequirementDTO registrationRequirementDTO) {
        RegistrationRequirement registrationRequirement = RegistrationRequirementConverter.toCreateRegistrationRequirement(registrationRequirementDTO);

        registrationRequirement = this.registrationRequirementRepository.save(registrationRequirement);
        return registrationRequirement;
    }

    @Override
    public void deleteById(Long id) {
        this.registrationRequirementRepository.deleteById(id);
    }

    @Override
    public List<RegistrationRequirementResponse> registrationRequirementList() {
        return registrationRequirementRepository.findAll().stream().map(RegistrationRequirementConverter::toCreateRegistrationRequirementResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String approveRegistrationRequest(Long id) {
        RegistrationRequirement req = registrationRequirementRepository.findById(id).orElseThrow(() -> new RegistrationRequirementNotFoundException("Registration requirement not found"));

        req.setPassword(passwordEncoder.encode(req.getPassword()));
        if (userService.existsByEmail(req.getEmail())) {
            registrationRequirementRepository.deleteById(id);
            throw new ResourceExistsException("User with email " + req.getEmail() + " already exists");
        }

        //            otkomentarisati ako se zeli isprobati lock baze
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//        }

        Patient patient = patientService.save(req);
        this.registrationRequirementRepository.deleteById(id);
        String subject = "Account registration";
        String answer = String.format(
                "    Patient account created successfully!\n" +
                        "    Please follow this link to activate account:\n" +
                        "    http://localhost:8080/sec/activate-account/%s"
                , patient.getId().toString());

        applicationEventPublisher.publishEvent(new MailRequestDTO(req.getEmail(), subject, answer));
        //emailService.sendSyncMailTo(patient.getEmail(), subject, answer);

        return "Patient registration approved";
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int rejectRegistrationRequest(Long id, String message) {
        RegistrationRequirement req = registrationRequirementRepository.findById(id).orElseThrow(() -> new RegistrationRequirementNotFoundException("Registration requirement not found"));

        if (userService.existsByEmail(req.getEmail())) {
            registrationRequirementRepository.deleteById(id);
            throw new ResourceExistsException("User with email " + req.getEmail() + " already exists");
        }

        //            otkomentarisati ako se zeli isprobati lock baze
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//        }

        String check = message.trim();
        if (check.equals("")) {
            return 1;
        }

        String subject = "Account registration";
        registrationRequirementRepository.deleteById(id);
        applicationEventPublisher.publishEvent(new MailRequestDTO(req.getEmail(), subject, message.trim()));
        //emailService.sendSyncMailTo(req.getEmail(), subject, message.trim());

        return 2;
    }

    @Override
    public RegistrationReqResponseDTO findAll(Integer pageCnt) {
        Pageable pageable = PageRequest.of(pageCnt, 10);
        Page<RegistrationRequirement> regReqs = registrationRequirementRepository.findAll(pageable);

        RegistrationReqResponseDTO regReqResponseDTO = RegistrationReqResponseDTO.builder()
                .reqs(regReqs.getContent().stream().map(RegistrationRequirementConverter::toCreateRegistrationRequirementResponse).collect(Collectors.toList()))
                .regReqsPageCnt(regReqs.getTotalPages())
                .build();
        return regReqResponseDTO;
    }

}
