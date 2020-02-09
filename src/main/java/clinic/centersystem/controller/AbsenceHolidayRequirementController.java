package clinic.centersystem.controller;

import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.AbsenceRequirementResponse;
import clinic.centersystem.model.AbsenceHolidayRequirement;
import clinic.centersystem.service.AbsenceHolidayRequirementServiceImpl;
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
@RequestMapping(value = "/absence-hol", produces = MediaType.APPLICATION_JSON_VALUE)
public class AbsenceHolidayRequirementController {

    private final AbsenceHolidayRequirementServiceImpl absenceHolidayRequirementService;

    public AbsenceHolidayRequirementController(AbsenceHolidayRequirementServiceImpl absenceHolidayRequirementService) {
        this.absenceHolidayRequirementService = absenceHolidayRequirementService;
    }

    @RequestMapping(method = POST, value = "/req")
    public ResponseEntity<String> submitAbsenceRequirement(@RequestBody AbsenceRequirementDTO absenceRequirementDTO) {
        String resp = absenceHolidayRequirementService.submitAbsenceRequirement(absenceRequirementDTO);
        if (resp.equals("Successfully created absence requirement")){
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = GET, value = "/my-abs-hol/{personnelId}")
    public ResponseEntity<List<AbsenceRequirementResponse>> getMyRequirements(@PathVariable Long personnelId) {
        return new ResponseEntity<>(absenceHolidayRequirementService.findAllByPersonnelId(personnelId), HttpStatus.OK);
    }
}
