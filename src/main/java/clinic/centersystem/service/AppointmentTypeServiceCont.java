package clinic.centersystem.service;

import clinic.centersystem.model.AppointmentType;
import clinic.centersystem.service.intf.AppointmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentTypeServiceCont {
    @Autowired
    private AppointmentTypeService appointmentTypeService;

    public List<AppointmentType> getAppointmentType(){ return this.appointmentTypeService.findAll();}
}
