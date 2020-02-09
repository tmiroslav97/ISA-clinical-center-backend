package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicConverter;
import clinic.centersystem.converter.DoctorConverter;
import clinic.centersystem.converter.PatientConverter;
import clinic.centersystem.dto.request.PatientSearchRequestDTO;
import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.DoctorResponse;
import clinic.centersystem.dto.response.PatientResponse;
import clinic.centersystem.dto.response.PatientSortResponseDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.AuthorityRepository;
import clinic.centersystem.repository.PatientRepository;
import clinic.centersystem.service.intf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Patient doesn't exist"));
    }

    @Override
    public List<Patient> findAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient findOneById(Long id){
        return patientRepository.findOneById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Patient save(RegistrationRequirement registrationRequirement) {
        Patient patient = PatientConverter.toCreatePatientFromRequest(registrationRequirement);
        List<Authority> auths = this.authorityService.findByName("ROLE_PATIENT");
        patient.setAuthorities(auths);
        patient.setVersion(0L);
        patient = patientRepository.save(patient);

        MedicalRecord medicalRecord = MedicalRecord.builder()
                .description("Zdravstevni karton pacijenta")
                .height(Float.valueOf(0))
                .weight(Float.valueOf(0))
                .bloodType("Nepoznato")
                .patient(patient)
                .build();

        medicalRecord = medicalRecordService.save(medicalRecord);
        patient.setMedicalRecord(medicalRecord);

        patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public PatientResponse patient(Long id) {
        Patient patient = this.findById(id);
        return PatientConverter.toCreatePatientResponseFromPatient(patient);
    }

    @Override
    public PatientResponse findPatientByAppId(Long id) {
        Patient patient = patientRepository.findPatientByAppId(id);
        return PatientConverter.toCreatePatientResponseFromPatient(patient);
    }

    @Override
    public PatientSortResponseDTO findAll(PatientSearchRequestDTO patientSearchRequestDTO) {
        Integer sort = patientSearchRequestDTO.getSort();
        Pageable pageable = null;
        if (sort == 0) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10);
        } else if (sort == 1) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("firstName").ascending());
        } else if (sort == 2) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("firstName").descending());
        } else if (sort == 3) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("lastName").ascending());
        } else if (sort == 4) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("lastName").descending());
        } else if (sort == 5) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("unoip").ascending());
        } else if (sort == 6) {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10, Sort.by("unoip").descending());
        } else {
            pageable = PageRequest.of(patientSearchRequestDTO.getPageCnt(), 10);
        }
        Page<Patient> patients = patientRepository.findAll(pageable);
        PatientSortResponseDTO patientSortResponseDTO = PatientSortResponseDTO.builder()
                .patients(patients.getContent().stream().map(PatientConverter::toCreatePatientResponseFromPatient).collect(Collectors.toList()))
                .patientPageCnt(patients.getTotalPages())
                .build();

        return patientSortResponseDTO;
    }

    @Override
    public List<PatientResponse> getPatients() {
        List<Patient> patients = this.findAll();
        List<PatientResponse> patientResponses = patients.stream().map(PatientConverter::toCreatePatientResponseFromPatient).collect(Collectors.toList());
        return patientResponses;
    }

    @Override
    public Set<PatientResponse> getPatientsByClinicId(Long clinicId) {
        Clinic clinic = clinicService.findById(clinicId);
        Set<Patient> patients = clinic.getPatients();
        Set<PatientResponse> patientResponses = patients.stream().map(PatientConverter::toCreatePatientResponseFromPatient).collect(Collectors.toSet());
        return patientResponses;
    }
}
