package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.MedicineRequestDTO;
import clinic.centersystem.model.Medicine;

import java.util.List;

public interface MedicineService {

    Medicine findById(Long id);

    List<Medicine> findAll();

    Medicine saveReq(MedicineRequestDTO medicineRequestDTO);

    Medicine save(Medicine medicine);
}
