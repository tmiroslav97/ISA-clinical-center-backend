package clinic.centersystem.controller;

import clinic.centersystem.dto.request.AppointmentTypeEditReqDTO;
import clinic.centersystem.dto.request.AppointmentTypeRequestDTO;
import clinic.centersystem.dto.request.AppointmentTypeSearchReqDTO;
import clinic.centersystem.model.AppointmentType;
import clinic.centersystem.service.AppointmentTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/appointment-type")
public class AppointmentTypeContoller {
    private final AppointmentTypeServiceImpl appointmentTypeService;

    public AppointmentTypeContoller(AppointmentTypeServiceImpl appointmentTypeService) {
        this.appointmentTypeService = appointmentTypeService;
    }

    @RequestMapping(method = GET, value = "/all/{clinicId}")
    public ResponseEntity<List<AppointmentType>> getAppointmentType(@PathVariable Long clinicId) {
        return new ResponseEntity<>(this.appointmentTypeService.getAppointmentType(clinicId), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value="/add-appointment-type/{clinicId}")
    public ResponseEntity<String>addAppointmentType(@RequestBody AppointmentTypeRequestDTO appointmentTypeRequestDTO, @PathVariable Long clinicId){
        return new ResponseEntity<>(this.appointmentTypeService.addAppointmentType(appointmentTypeRequestDTO, clinicId), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/delete-appointment-type/{appointmentId}")
    public ResponseEntity<String>deleteAppointmentType(@PathVariable Long appointmentId){
        return new ResponseEntity<>(this.appointmentTypeService.deleteAppointmentType(appointmentId), HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/edit-appointment-type")
    public ResponseEntity<String>editAppointmentType(@RequestBody AppointmentTypeEditReqDTO appointmentTypeEditReqDTO){
        return new ResponseEntity<>(this.appointmentTypeService.editAppointmentType(appointmentTypeEditReqDTO),HttpStatus.OK);
    }

    @RequestMapping(method = POST, value = "/search-appointment-type")
    public ResponseEntity<List<AppointmentType>>searchAppointmentType(@RequestBody AppointmentTypeSearchReqDTO appointmentTypeSearchReqDTO){
        return new ResponseEntity<List<AppointmentType>>(this.appointmentTypeService.searchAppointmentType(appointmentTypeSearchReqDTO), HttpStatus.OK);
    }






}
