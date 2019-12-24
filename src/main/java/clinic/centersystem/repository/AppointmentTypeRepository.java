package clinic.centersystem.repository;

import clinic.centersystem.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTypeRepository  extends JpaRepository<AppointmentType, Long> {
}
