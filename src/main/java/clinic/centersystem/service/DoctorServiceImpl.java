package clinic.centersystem.service;

import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.dto.response.DoctorResponse;
import clinic.centersystem.model.Doctor;
import clinic.centersystem.repository.DoctorRepository;
import clinic.centersystem.service.intf.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void remove(Long id){this.doctorRepository.deleteById(id);}

    @Override
    public DoctorResponse getDoctorById(Long id){
        Doctor doctor = this.findById(id);
        return DoctorConverter.toCreateDoctorResponseFromDoctor(doctor);
    }

    @Override
    public List<DoctorResponse> getDoctors() {
        List<Doctor> doctors = this.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
        for (Doctor doctor : doctors) {
            doctorResponses.add(DoctorConverter.toCreateDoctorResponseFromDoctor(doctor));
        }
        return doctorResponses;
    }

    @Override
    public List<Doctor> searchDoctorByName(String name){
        List<Doctor> listDoctors = new ArrayList<>();
        List<Doctor>doctors= this.findAll();
        for (Doctor doctor:doctors){
            if(doctor.getFirstName().toLowerCase().contains(name.toLowerCase())){
                listDoctors.add(doctor);
            }
        }
        return listDoctors;
    }


}
