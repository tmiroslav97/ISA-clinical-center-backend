package clinic.centersystem.controller;


import clinic.centersystem.converter.RegistrationRequirementConverter;
import clinic.centersystem.dto.response.RegistrationReqResponseDTO;
import clinic.centersystem.dto.response.RegistrationRequirementResponse;
import clinic.centersystem.service.RegistrationRequirementServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/reg", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationRequirementController {
    private final RegistrationRequirementServiceImpl registrationRequirementService;

    public RegistrationRequirementController(RegistrationRequirementServiceImpl registrationRequirementService) {
        this.registrationRequirementService = registrationRequirementService;
    }

    @RequestMapping(method = GET, value = "/regreqs")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<List<RegistrationRequirementResponse>> registrationReqs() {
        return new ResponseEntity<>(this.registrationRequirementService.registrationRequirementList(), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/regreqs/{pageCnt}")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<RegistrationReqResponseDTO> getRegReqs(@PathVariable Integer pageCnt) {
        return new ResponseEntity<>(this.registrationRequirementService.findAll(pageCnt), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/approve/{reqId}")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> approveRegistrationRequest(@PathVariable Long reqId) {
        return new ResponseEntity<>(registrationRequirementService.approveRegistrationRequest(reqId), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/reject/{reqId}/{msg}")
    @PreAuthorize("hasRole('CCADMIN')")
    public ResponseEntity<String> rejectRegistrationRequest(@PathVariable Long reqId, @PathVariable String msg) {
        int flag = registrationRequirementService.rejectRegistrationRequest(reqId, msg);

        if (flag == 1) {
            return new ResponseEntity<>("Missing message", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Patient registration rejected", HttpStatus.OK);
        }

    }
}
