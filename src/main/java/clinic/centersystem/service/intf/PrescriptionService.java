package clinic.centersystem.service.intf;

import clinic.centersystem.dto.response.PrescriptionResponse;
import clinic.centersystem.model.Prescription;

import java.util.List;
import java.util.Set;

public interface PrescriptionService {
    Prescription findById(Long id);

    List<Prescription> findAll();

    Prescription save(Prescription prescription);

    List<PrescriptionResponse> findAllByClinicIdNotValidated(Long id);

    List<Prescription> saveAll(List<Prescription> prescriptions);

    String rewritePrescription(Long nurseId, Long prescriptionId);
}
