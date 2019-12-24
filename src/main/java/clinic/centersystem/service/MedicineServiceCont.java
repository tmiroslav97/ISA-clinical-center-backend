package clinic.centersystem.service;

import clinic.centersystem.model.Medicine;
import clinic.centersystem.model.MedicineRecord;
import clinic.centersystem.service.intf.MedicineRecordService;
import clinic.centersystem.service.intf.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineServiceCont {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRecordService medicineRecordService;

    public List<Medicine> getMedicines() {
        return medicineService.findAll();
    }
}
