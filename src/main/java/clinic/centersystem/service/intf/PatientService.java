package clinic.centersystem.service.intf;


import clinic.centersystem.dto.request.PatientSearchRequestDTO;
import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.PatientResponse;
import clinic.centersystem.dto.response.PatientSortResponseDTO;
import clinic.centersystem.model.Patient;
import clinic.centersystem.model.RegistrationRequirement;

import java.util.List;
import java.util.Set;

public interface PatientService {
    Patient findById(Long id);

    List<Patient> findAll();

    Patient findOneById(Long id);

    Patient save(RegistrationRequirement registrationRequirement);

    Patient save(Patient patient);

    List<PatientResponse> getPatients();

    Set<PatientResponse> getPatientsByClinicId(Long clinicId);

    PatientResponse patient(Long id);

    PatientResponse findPatientByAppId(Long id);

    PatientSortResponseDTO findAll(PatientSearchRequestDTO patientSearchRequestDTO);
}
