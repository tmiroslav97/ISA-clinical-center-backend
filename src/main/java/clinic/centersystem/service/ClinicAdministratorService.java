package clinic.centersystem.service;

import clinic.centersystem.converter.AppointmentTypeConverter;
import clinic.centersystem.converter.ClinicAdminConverter;
import clinic.centersystem.converter.ClinicCenterAdminConverter;
import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.dto.request.AppointmentTypeRequestDTO;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.dto.response.AppointmentTypeResponse;
import clinic.centersystem.dto.response.ClinicAdministratoreResponse;
import clinic.centersystem.dto.response.DoctorResponse;
import clinic.centersystem.model.AppointmentType;
import clinic.centersystem.model.Authority;
import clinic.centersystem.model.ClinicAdmin;
import clinic.centersystem.model.Doctor;
import clinic.centersystem.service.intf.AuthorityService;
import clinic.centersystem.service.intf.ClinicAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicAdministratorService {
    @Autowired
    private ClinicAdminService clinicAdministratorService;
    @Autowired
    private DoctorServiceImpl doctorService;
    @Autowired
    private AppointmentTypeServiceImpl appointmentTypeService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    public ClinicAdministratoreResponse clinicAdministrator(Long id) {
        ClinicAdmin clinicAdmin = this.clinicAdministratorService.findById(id);
        return ClinicAdminConverter.toCreateClinicAdminResponse(clinicAdmin);
    }


    public String addDoctor(DoctorRequestDTO doctorRequestDTO) {
        doctorRequestDTO.setPassword1(passwordEncoder.encode(doctorRequestDTO.getPassword1()));

        Doctor doc = DoctorConverter.toCreateDoctorFromDoctorRequest(doctorRequestDTO);

        List<Authority> auths = this.authorityService.findByName("ROLE_DOCTOR");
        doc.setAuthorities(auths);

        Doctor doctor = this.doctorService.save(doc);

        return "Successfully added doctor";
    }

    public String addAppointmentType(AppointmentTypeRequestDTO appointmentTypeRequestDTO) {
        AppointmentType appointment = AppointmentTypeConverter.toCreateAppointmentTypeFromRequest(appointmentTypeRequestDTO);
        AppointmentType appointmentType = appointmentTypeService.save(appointment);

        return "Successfully added appointment type";
    }
    /*public String addAppointmentType(AppointmentTypeRequestDTO diagnoseRequestDTO) {
        AppointmentType appointment = diagnoseService.save(diagnoseRequestDTO);

        return "Successfully added apType;
    }*/

    public String deleteDoctor(Long id) {
        List<Doctor> doctors = this.doctorService.findAll();
        Doctor doctor = this.doctorService.findById(id);
        doctors.remove(doctor);
        return "Successfully deleted doctor";
    }

    public String deleteAppointmentType(Long id) {
        List<AppointmentType> appointmentTypes = this.appointmentTypeService.findAll();
        AppointmentType appointmentType = this.appointmentTypeService.findById(id);
        appointmentTypes.remove(appointmentType);
        return "Successfully deleted doctor";
    }

    public List<DoctorResponse> getDoctors() {
        List<Doctor> doctors = this.doctorService.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
        for (Doctor doctor : doctors) {
            doctorResponses.add(DoctorConverter.toCreateDoctorResponseFromDoctor(doctor));
        }
        return doctorResponses;
    }

    /*public List<AppointmentTypeResponse> getAppointmentTypes() {
        List<AppointmentType> appointmentTypes = this.appointmentTypeService.findAll();
        List<AppointmentTypeResponse> appointmentTypeResponses = new ArrayList<AppointmentTypeResponse>();
        for (AppointmentType appointmentType : appointmentTypes) {
            appointmentTypeResponses.add(AppointmentTypeConverter.toCreateAppointmentTypeFromAppointmentType(appointmentType));
        }
        return appointmentTypeResponses;
    }*/


}
