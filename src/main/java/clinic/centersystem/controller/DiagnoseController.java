package clinic.centersystem.controller;

import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.model.Diagnose;
import clinic.centersystem.service.DiagnoseServiceCont;
import clinic.centersystem.service.MedicineServiceCont;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/diag", produces = MediaType.APPLICATION_JSON_VALUE)
public class DiagnoseController {

    private final DiagnoseServiceCont diagnoseServiceCont;

    public DiagnoseController(DiagnoseServiceCont diagnoseServiceCont) {
        this.diagnoseServiceCont = diagnoseServiceCont;
    }

    @RequestMapping(method = GET, value = "/all")
    public ResponseEntity<List<Diagnose>> getDiagnoses() {
        return new ResponseEntity<>(this.diagnoseServiceCont.getDiagnoses(), HttpStatus.OK);
    }

}
