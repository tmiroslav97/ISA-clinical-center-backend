package clinic.centersystem.repository;

import clinic.centersystem.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentTypeRepository  extends JpaRepository<AppointmentType, Long> {

    List<AppointmentType> findAllByClinicId(Long id);
    List<AppointmentType> findByClinicIdAndTypeIgnoreCase(Long id, String type);

}
