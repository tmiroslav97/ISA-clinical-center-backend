package clinic.centersystem.service;

import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.dto.request.DoctorSearchReqDTO;
import clinic.centersystem.dto.response.DoctorResponse;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.DoctorRepository;
import clinic.centersystem.service.intf.AppointmentService;
import clinic.centersystem.service.intf.AuthorityService;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public Doctor findById(Long id) {
        return this.doctorRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findAllOnClinic(Long id) {
        return null;
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor save(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = DoctorConverter.toCreateDoctorFromDoctorRequest(doctorRequestDTO);
        doctor = this.doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Doctor findOneById(Long id){
        return doctorRepository.findOneById(id);
    }


    @Override
    public void remove(Long id) {
        this.doctorRepository.deleteById(id);
    }

    @Override
    public DoctorResponse getDoctorById(Long id) {
        Doctor doctor = this.findById(id);
        return DoctorConverter.toCreateDoctorResponseFromDoctor(doctor);
    }

    @Override
    public List<DoctorResponse> getDoctors() {
        return this.findAll().stream().map(DoctorConverter::toCreateDoctorResponseFromDoctor).collect(Collectors.toList());
    }

    @Override
    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> listDoctors = new ArrayList<>();
        List<Doctor> doctors = this.findAll();
        for (Doctor doctor : doctors) {
            if (doctor.getFirstName().toLowerCase().contains(name.toLowerCase())) {
                listDoctors.add(doctor);
            }
        }
        return listDoctors;
    }

    @Override
    public List<DoctorResponse> findByClinicId(Long clinicId) {
        return doctorRepository.findByClinicId(clinicId).stream().map(DoctorConverter::toCreateDoctorResponseFromDoctor).collect(Collectors.toList());
    }

    public List<Doctor> searchDoctors(String name) {
        List<Doctor> listDoctors = new ArrayList<>();
        List<Doctor> doctors = this.doctorRepository.findAll();
        for (Doctor doctor : doctors) {
            if (doctor.getFirstName().toLowerCase().contains(name.toLowerCase())) {
                listDoctors.add(doctor);
            }
        }
        return listDoctors;
    }

    public String addDoctorOnClinic(DoctorRequestDTO doctorRequestDTO, Long id){
        Doctor doctor = DoctorConverter.toCreateDoctorFromDoctorRequest(doctorRequestDTO);
        doctor.setEmail(doctorRequestDTO.getEmail());
        if(doctorRequestDTO.getPassword1().equals(doctorRequestDTO.getPassword2())){
            doctor.setPassword(doctorRequestDTO.getPassword1());
        }else{
            return "Password wasn't confirmed";
        }
        doctor.setFirstName(doctorRequestDTO.getFirstName());
        doctor.setLastName(doctorRequestDTO.getLastName());
        doctor.setStartTime(doctorRequestDTO.getStartTime());
        doctor.setEndTime(doctorRequestDTO.getEndTime());
        Clinic clinic  = this.clinicService.findById(id);
        doctor.setClinic(clinic);
        List<Authority> auths = this.authorityService.findByName("ROLE_DOCTOR");
        doctor.setAuthorities(auths);
        doctor.setFirstLog(true);
        doctorRepository.save(doctor);
        return "Successfully added doctor on clinic";
    }

    public  String deleteDoctor (Long doctorId){
        if(appointmentService.existsByDoctorId(doctorId)){
            return "Can't delete doctor, beacause he has appointment";
        }else {
            doctorRepository.deleteById(doctorId);
            return  "Successfully deleted doctor";
        }
    }

    public List<DoctorResponse> searchDoctor(DoctorSearchReqDTO doctorSearchReqDTO){
        return doctorRepository.findByClinicIdAndLastNameIgnoringCase(doctorSearchReqDTO.getClinicId(), doctorSearchReqDTO.getLastName()).stream().map(DoctorConverter::toCreateDoctorResponseFromDoctor).collect(Collectors.toList());
    }

    public String addDoctor(DoctorRequestDTO doctorRequestDTO) {
        doctorRequestDTO.setPassword1(passwordEncoder.encode(doctorRequestDTO.getPassword1()));

        Doctor doc = DoctorConverter.toCreateDoctorFromDoctorRequest(doctorRequestDTO);

        List<Authority> auths = this.authorityService.findByName("ROLE_DOCTOR");
        doc.setAuthorities(auths);

        Doctor doctor = this.doctorRepository.save(doc);

        return "Successfully added doctor";
    }


}
