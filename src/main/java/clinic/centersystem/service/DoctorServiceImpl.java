package clinic.centersystem.service;

import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.model.Doctor;
import clinic.centersystem.repository.DoctorRepository;
import clinic.centersystem.service.intf.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;


    @Override
    public Doctor findById(Long id){return this.doctorRepository.findById(id).orElseGet(null);}

    @Override
    public List<Doctor> findAll() {return this.doctorRepository.findAll();}

    @Override
    public List<Doctor> findAllOnClinic(Long id){return null;}

    @Override
    public Doctor save(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = DoctorConverter.toCreateDoctorFromDoctorRequest(doctorRequestDTO);
        doctor = this.doctorRepository.save(doctor);
        return doctor;
    }

    @Override
    public Doctor save(Doctor doctor){return this.doctorRepository.save(doctor);}

    public void remove(Long id){this.doctorRepository.deleteById(id);}



}
