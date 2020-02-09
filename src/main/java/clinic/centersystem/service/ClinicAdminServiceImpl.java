package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicAdminConverter;
import clinic.centersystem.dto.request.ClinicAdminEditReqDTO;
import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.response.ClinicAdministratoreResponse;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.ClinicAdminRepository;
import clinic.centersystem.service.intf.ClinicAdminService;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicAdminServiceImpl implements ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Autowired
    private DoctorServiceImpl doctorService;

    @Autowired
    private AppointmentTypeServiceImpl appointmentTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private ClinicAdminService clinicAdminService;

    @Autowired
    private EmailService emailService;

    @Override
    public ClinicAdmin findById(Long id) {

        return this.clinicAdminRepository.findById(id).orElseThrow(()-> new ResourceNotExistsException("Clinic admin doesn't exist"));
    }

    @Override
    public List<ClinicAdmin> findAll() {

        return this.clinicAdminRepository.findAll();
    }

    @Override
    public ClinicAdmin save(ClinicAdminReqDTO clinicAdminReqDTO) {
        ClinicAdmin clinicAdmin = ClinicAdminConverter.toCreateClinicAdminFromRequest(clinicAdminReqDTO);

        clinicAdmin = this.clinicAdminRepository.save(clinicAdmin);

        return clinicAdmin;
    }

    @Override
    public ClinicAdmin saveClinicAdmin(ClinicAdmin clinicAdmin) {
        clinicAdmin = this.clinicAdminRepository.save(clinicAdmin);

        return clinicAdmin;
    }

    public ClinicAdministratoreResponse clinicAdministrator(Long id) {
        ClinicAdmin clinicAdmin = this.findById(id);
        return ClinicAdminConverter.toCreateClinicAdminResponse(clinicAdmin);
    }

    public String deleteDoctor(Long id) {
        List<Doctor> doctors = this.doctorService.findAll();
        Doctor doctor = this.doctorService.findById(id);
        doctors.remove(doctor);
        return "Successfully deleted doctor";
    }

    @Override
    public String registerClinicAdmin(ClinicAdminReqDTO clinicAdminReqDTO) {
        String password = clinicAdminReqDTO.getPassword();
        clinicAdminReqDTO.setPassword(this.passwordEncoder.encode(clinicAdminReqDTO.getPassword()));
        if (this.userService.existsByEmail(clinicAdminReqDTO.getEmail())) {
            throw new ResourceExistsException("User with email " + clinicAdminReqDTO.getEmail() + " already exists");
        }
        ClinicAdmin clinicAdmin = this.clinicAdminService.save(clinicAdminReqDTO);

        Clinic clinic = this.clinicService.findById(clinicAdminReqDTO.getClinicId());

        clinic.getClinicAdmins().add(clinicAdmin);
        clinicAdmin.setClinic(clinic);

        clinicAdmin = this.clinicAdminService.saveClinicAdmin(clinicAdmin);
        clinic = this.clinicService.saveClinic(clinic);

        String subject = "Account registration";
        String answer = String.format(
                "Your are registered as clinic administrator" +
                        "\nYour email(username) is: " + clinicAdmin.getEmail() +
                        "\nYou password is: " + password);

        emailService.sendMailTo(clinicAdmin.getEmail(), subject, answer);

        return "Clinic admin successfully registered";
    }

    public String editClinicAdmin(ClinicAdminEditReqDTO clinicAdminEditReqDTO){
        ClinicAdmin clinicAdmin = this.findById(clinicAdminEditReqDTO.getId());
        clinicAdmin.setFirstName(clinicAdminEditReqDTO.getFirstName());
        clinicAdmin.setLastName(clinicAdminEditReqDTO.getLastName());
        clinicAdmin.setEmail(clinicAdminEditReqDTO.getEmail());
        clinicAdminRepository.save(clinicAdmin);
        return "Successfully edited users profile";
    }



}
