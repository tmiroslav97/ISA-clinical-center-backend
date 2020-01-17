package clinic.centersystem.service;

import clinic.centersystem.model.Prescription;
import clinic.centersystem.repository.PrescriptionRepository;
import clinic.centersystem.service.intf.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Prescription findById(Long id) {
        return this.prescriptionRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Prescription> findAll() {
        return this.prescriptionRepository.findAll();
    }

    @Override
    public Prescription save(Prescription prescription) {
        return this.prescriptionRepository.save(prescription);
    }
}
