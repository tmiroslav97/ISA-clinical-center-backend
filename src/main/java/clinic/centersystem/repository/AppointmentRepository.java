package clinic.centersystem.repository;

import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.enumeration.AppStateEnum;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorIdAndAppState(Long id, AppStateEnum appStateEnum);

    Boolean existsByDoctorId(Long id);
}
