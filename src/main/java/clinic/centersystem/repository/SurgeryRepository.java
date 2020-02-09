package clinic.centersystem.repository;

import clinic.centersystem.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {

}
