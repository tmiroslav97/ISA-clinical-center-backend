package clinic.centersystem.controller;

import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.service.NurseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/nurse", produces = MediaType.APPLICATION_JSON_VALUE)
public class NurseController {

    private final NurseServiceImpl nurseService;


    public NurseController(NurseServiceImpl nurseService) {
        this.nurseService = nurseService;
    }

    @RequestMapping(method = GET, value = "/{nurseId}")
    public ResponseEntity<NurseResponse> getNurseById(@PathVariable Long nurseId) {
        return new ResponseEntity<>(this.nurseService.getNurseById(nurseId), HttpStatus.OK);
    }



}
