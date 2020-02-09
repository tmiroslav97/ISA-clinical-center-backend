package clinic.centersystem.service.intf;

import clinic.centersystem.model.AppointmentType;

import java.util.List;

public interface AppointmentTypeService {
    AppointmentType findById(Long id);

    List<AppointmentType> findAll();

    AppointmentType save(AppointmentType appointmentType);

    public void remove(Long id);

    List<AppointmentType> getAppointmentType(Long clinicId);
}
