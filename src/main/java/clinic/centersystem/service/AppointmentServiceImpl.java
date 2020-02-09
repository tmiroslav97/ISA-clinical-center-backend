package clinic.centersystem.service;

import clinic.centersystem.dto.response.AppointmentResponseDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.enumeration.AppStateEnum;
import clinic.centersystem.repository.AppointmentRepository;
import clinic.centersystem.service.intf.AppointmentService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Appointment doesn't exist"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> findAllByDoctorIdAndAppState(Long id, AppStateEnum appState) {
        return appointmentRepository.findAllByDoctorIdAndAppState(id, appState);
    }

    @Override
    public Boolean existsByDoctorId (Long id){
        return  appointmentRepository.existsByDoctorId(id);
    }

    @Override
    public AppointmentResponseDTO getById(Long id) {
        Appointment appointment = this.findById(id);
        AppointmentResponseDTO appointmentResponseDTO = AppointmentResponseDTO.builder()
                .appState(appointment.getAppState().toString())
                .patient(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName())
                .build();

        return appointmentResponseDTO;
    }
}
