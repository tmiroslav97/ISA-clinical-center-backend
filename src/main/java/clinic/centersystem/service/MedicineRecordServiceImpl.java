package clinic.centersystem.service;

import clinic.centersystem.model.MedicineRecord;
import clinic.centersystem.repository.MedicineRecordRepository;
import clinic.centersystem.service.intf.MedicineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicineRecordServiceImpl implements MedicineRecordService {

    @Autowired
    private MedicineRecordRepository medicineRecordRepository;


    @Override
    public MedicineRecord findById(Long id) {
        return this.medicineRecordRepository.findById(id).orElseGet(null);
    }

    @Override
    public MedicineRecord save(MedicineRecord medicineRecord) {
        return this.medicineRecordRepository.save(medicineRecord);
    }
}
