package clinic.centersystem.controller;


import clinic.centersystem.dto.request.MedicalRecordRequestDTO;
import clinic.centersystem.model.Diagnose;
import clinic.centersystem.model.MedicalRecord;
import clinic.centersystem.service.MedicalRecordServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/med-rec", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalRecordController {

    private final MedicalRecordServiceImpl medicalRecordService;

    public MedicalRecordController(MedicalRecordServiceImpl medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable Long id) {
        return new ResponseEntity<>(this.medicalRecordService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/app/{id}", method = RequestMethod.GET)
    public ResponseEntity<MedicalRecord> getMedicalRecordByAppId(@PathVariable Long id) {
        return new ResponseEntity<>(this.medicalRecordService.findByAppId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<String> editMedicalRecord(@RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO) {
        return new ResponseEntity<>(this.medicalRecordService.editMedicalRecord(medicalRecordRequestDTO), HttpStatus.OK);
    }
}
