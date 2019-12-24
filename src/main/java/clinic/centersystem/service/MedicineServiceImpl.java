package clinic.centersystem.service;

import clinic.centersystem.converter.MedicineConverter;
import clinic.centersystem.dto.request.MedicineRequestDTO;
import clinic.centersystem.model.Medicine;
import clinic.centersystem.repository.MedicineRepository;
import clinic.centersystem.service.intf.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine findById(Long id) {
        return this.medicineRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Medicine> findAll() {
        return this.medicineRepository.findAll();
    }

    @Override
    public Medicine saveReq(MedicineRequestDTO medicineRequestDTO) {
        Medicine medicine = MedicineConverter.toCreateMedicineFromRequest(medicineRequestDTO);
        medicine = this.medicineRepository.save(medicine);
        return medicine;
    }

    @Override
    public Medicine save(Medicine medicine) {
        return this.medicineRepository.save(medicine);
    }
    
}
