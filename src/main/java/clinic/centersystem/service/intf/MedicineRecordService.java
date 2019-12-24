package clinic.centersystem.service.intf;

import clinic.centersystem.model.MedicineRecord;

public interface MedicineRecordService {

    MedicineRecord findById(Long id);

    MedicineRecord save(MedicineRecord medicineRecord);

}
