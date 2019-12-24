package clinic.centersystem.controller;


import clinic.centersystem.model.Diagnose;
import clinic.centersystem.model.Medicine;
import clinic.centersystem.model.MedicineRecord;
import clinic.centersystem.service.MedicineServiceCont;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/medic")
public class MedicineController {

    private final MedicineServiceCont medicineServiceCont;

    public MedicineController(MedicineServiceCont medicineServiceCont) {
        this.medicineServiceCont = medicineServiceCont;
    }

    @RequestMapping(method = GET, value = "/all")
    public ResponseEntity<List<Medicine>> getMedicines() {
        return new ResponseEntity<>(this.medicineServiceCont.getMedicines(), HttpStatus.OK);
    }

}
