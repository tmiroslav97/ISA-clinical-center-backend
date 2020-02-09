package clinic.centersystem.controller;

import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.AbsenceHolidayRequirement;
import clinic.centersystem.service.PersonnelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/personnel", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonnelController {

    private final PersonnelServiceImpl personnelService;

    public PersonnelController(PersonnelServiceImpl personnelService) {
        this.personnelService = personnelService;
    }

    @RequestMapping(method = GET, value = "/my-cal/{personnelId}")
    public ResponseEntity<CalendarResponse> getMyCalendar(@PathVariable Long personnelId) {
        return new ResponseEntity<>(this.personnelService.getMyCalendar(personnelId), HttpStatus.OK);
    }
}
