package clinic.centersystem.repository;


import clinic.centersystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByClinicId(Long id);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Doctor findOneById(Long id);

    List<Doctor> findByClinicIdAndLastNameIgnoringCase(Long clinicId, String lastName);
}
