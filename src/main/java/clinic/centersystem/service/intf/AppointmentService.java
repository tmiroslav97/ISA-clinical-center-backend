package clinic.centersystem.service.intf;

import clinic.centersystem.dto.response.AppointmentResponseDTO;
import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.enumeration.AppStateEnum;

import java.util.List;

public interface AppointmentService {

    Appointment findById(Long id);

    Appointment save(Appointment appointment);

    List<Appointment> findAllByDoctorIdAndAppState(Long id, AppStateEnum appState);

    AppointmentResponseDTO getById(Long id);

    Boolean existsByDoctorId (Long id);
}
