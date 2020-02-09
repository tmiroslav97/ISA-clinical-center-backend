package clinic.centersystem.controller;

import clinic.centersystem.dto.response.PrescriptionResponse;
import clinic.centersystem.service.PrescriptionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescriptionController {

    private final PrescriptionServiceImpl prescriptionService;

    public PrescriptionController(PrescriptionServiceImpl prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @RequestMapping(method = POST, value = "/rewrite/{nurseId}/{prescriptionId}")
    public ResponseEntity<String> rewritePrescription(@PathVariable Long nurseId, @PathVariable Long prescriptionId) {
        return new ResponseEntity<>(prescriptionService.rewritePrescription(nurseId, prescriptionId), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/all/{clinicId}")
    public ResponseEntity<List<PrescriptionResponse>> getPrescriptionsNotValidated(@PathVariable Long clinicId) {
        return new ResponseEntity<>(prescriptionService.findAllByClinicIdNotValidated(clinicId), HttpStatus.OK);
    }
}
