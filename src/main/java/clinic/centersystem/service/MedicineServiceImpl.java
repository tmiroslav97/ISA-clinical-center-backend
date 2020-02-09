package clinic.centersystem.service;

import clinic.centersystem.dto.response.MedicineResponseDTO;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Medicine;
import clinic.centersystem.model.SurgeryRequirement;
import clinic.centersystem.repository.MedicineRepository;
import clinic.centersystem.service.intf.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine findById(Long id) {
        return medicineRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Medicine doesn't exists"));
    }

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    @Override
    public MedicineResponseDTO findAll(Integer pageCnt) {
        Pageable pageable = PageRequest.of(pageCnt, 10);
        Page<Medicine> medicines = medicineRepository.findAll(pageable);

        MedicineResponseDTO medicineResponseDTO = MedicineResponseDTO.builder()
                .medicines(medicines.getContent())
                .medicinePageCnt(medicines.getTotalPages())
                .build();
        return medicineResponseDTO;
    }

    @Override
    public Medicine save(Medicine medicine) {
        if (medicineRepository.existsByCode(medicine.getCode())) {
            throw new ResourceExistsException("Medicine with code " + medicine.getCode() + " already exists");
        }
        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> findAllByIdIn(Set<Long> medicines) {
        return medicineRepository.findAllByIdIn(medicines);
    }
}
