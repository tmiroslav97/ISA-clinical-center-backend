package clinic.centersystem.service;

import clinic.centersystem.dto.request.MedicalRecordRequestDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.MedicalRecord;
import clinic.centersystem.repository.MedicalRecordRepository;
import clinic.centersystem.service.intf.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord findById(Long id) {
        return medicalRecordRepository.findById(id).orElseThrow(()-> new ResourceNotExistsException("Medical record doesn't exist"));
    }

    @Override
    public MedicalRecord findByPatientId(Long id) {
        return medicalRecordRepository.findByPatientId(id);
    }

    @Override
    public MedicalRecord save(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord findByAppId(Long id) {
        return medicalRecordRepository.findByAppId(id);
    }

    @Override
    public String editMedicalRecord(MedicalRecordRequestDTO medicalRecordRequestDTO) {
        MedicalRecord medicalRecord = this.findById(medicalRecordRequestDTO.getId());
        if(medicalRecord==null){
            throw new ResourceNotExistsException("Can't find medical record");
        }
        medicalRecord.setBloodType(medicalRecordRequestDTO.getBloodType());
        medicalRecord.setWeight(medicalRecordRequestDTO.getWeight());
        medicalRecord.setHeight(medicalRecordRequestDTO.getHeight());
        medicalRecord.setDescription(medicalRecordRequestDTO.getDescription());

        medicalRecordRepository.save(medicalRecord);
        return "Successfuly edited";
    }
}
