package clinic.centersystem.converter;

import clinic.centersystem.dto.request.AppointmentTypeEditReqDTO;
import clinic.centersystem.model.AppointmentType;

public class AppointmentTypeEditConverter {
    public static AppointmentType toEditAppointmentTypeFromRequest(AppointmentTypeEditReqDTO appointmentTypeEditReqDTO){
        return  AppointmentType.builder()
                .id(appointmentTypeEditReqDTO.getId())
                .type(appointmentTypeEditReqDTO.getType())
                .build();
    }
}
