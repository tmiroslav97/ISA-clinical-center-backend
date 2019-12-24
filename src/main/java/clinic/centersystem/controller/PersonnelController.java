package clinic.centersystem.controller;

import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.dto.response.PatientResponse;
import clinic.centersystem.model.AbsenceRequirement;
import clinic.centersystem.service.PersonnelServiceCont;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/personnel", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonnelController {

    private final PersonnelServiceCont personnelServiceCont;

    public PersonnelController(PersonnelServiceCont personnelServiceCont) {
        this.personnelServiceCont = personnelServiceCont;
    }

    @RequestMapping(method = POST, value = "/abs-hol")
    public ResponseEntity<String> submitAbsenceRequirement(@RequestBody AbsenceRequirementDTO absenceRequirementDTO) {
        return new ResponseEntity<>(this.personnelServiceCont.submitAbsenceRequirement(absenceRequirementDTO), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/my-abs-hol/{personnelId}")
    public ResponseEntity<Set<AbsenceRequirement>> getMyRequirements(@PathVariable Long personnelId) {
        return new ResponseEntity<>(this.personnelServiceCont.getMyRequirements(personnelId), HttpStatus.OK);
    }


    @RequestMapping(method = GET, value = "/my-cal/{personnelId}")
    public ResponseEntity<CalendarResponse> getMyCalendar(@PathVariable Long personnelId) {
        return new ResponseEntity<>(this.personnelServiceCont.getMyCalendar(personnelId), HttpStatus.OK);
    }
}
