package clinic.centersystem.service.intf;

import clinic.centersystem.model.Prescription;

import java.util.List;

public interface PrescriptionService {
    Prescription findById(Long id);

    List<Prescription> findAll();

    Prescription save(Prescription prescription);

}
