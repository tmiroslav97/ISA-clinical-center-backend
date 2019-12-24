package clinic.centersystem.service;


import clinic.centersystem.converter.ClinicConverter;
import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.converter.PatientConverter;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.DoctorResponse;
import clinic.centersystem.dto.response.PatientResponse;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.Doctor;
import clinic.centersystem.model.Patient;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.DoctorService;
import clinic.centersystem.service.intf.PatientService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class PatientServiceCont {

    @Autowired
    private PatientService patientService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private DoctorService doctorService;

    public PatientResponse patient(Long id) {
        Patient patient = this.patientService.findById(id);
        return PatientConverter.toCreatePatientResponseFromPatient(patient);
    }

    public List<DoctorResponse> getDoctors() {
        List<Doctor> doctors = this.doctorService.findAll();
        List<DoctorResponse> doctorResponses = new ArrayList<DoctorResponse>();
        for (Doctor doctor : doctors) {
            doctorResponses.add(DoctorConverter.toCreateDoctorResponseFromDoctor(doctor));
        }
        return doctorResponses;
    }

    public List<ClinicResponse> getClinics() {
        List<Clinic> clinics = this.clinicService.findAll();
        List<ClinicResponse> clinicResponses = new ArrayList<ClinicResponse>();
        for (Clinic clinic : clinics) {
            clinicResponses.add(ClinicConverter.toCreateClinicResponseFromClinic(clinic));
        }
        return clinicResponses;
    }

    public List<Doctor> searchDoctors(String name)  {
        List<Doctor> listDoctors = new ArrayList<>();
        List<Doctor> doctors = this.doctorService.findAll();
        for (Doctor doctor : doctors) {
            if(doctor.getFirstName().toLowerCase().contains(name.toLowerCase())) {
                listDoctors.add(doctor);
            }
        }
        return listDoctors;
    }

    public List<Clinic> searchClinics(String name) {
        List<Clinic> listClinics = new ArrayList<>();
        List<Clinic> clinics = this.clinicService.findAll();
        for(Clinic clinic : clinics) {
            if(clinic.getName().toLowerCase().contains(name.toLowerCase())) {
                listClinics.add(clinic);
            }
        }
        return listClinics;
    }



    public List<PatientResponse> getPatients() {
        List<Patient> patients = this.patientService.findAll();
        List<PatientResponse> patientResponses = new ArrayList<PatientResponse>();
        for (Patient patient : patients) {
            patientResponses.add(PatientConverter.toCreatePatientResponseFromPatient(patient));
        }

        return patientResponses;
    }

    public Set<PatientResponse> getPatientsByClinicId(Long clinicId) {
        Clinic clinic = this.clinicService.findById(clinicId);
        Set<Patient> patients = clinic.getPatients();
        Set<PatientResponse> patientResponses = new HashSet<PatientResponse>();
        for (Patient patient : patients) {
            patientResponses.add(PatientConverter.toCreatePatientResponseFromPatient(patient));
        }

        return patientResponses;
    }

}
