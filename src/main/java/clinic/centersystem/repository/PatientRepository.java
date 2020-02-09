package clinic.centersystem.repository;

import clinic.centersystem.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select pat from Patient pat, Appointment app where app.id=(?1) and pat=app.patient")
    Patient findPatientByAppId(Long id);

    Page<Patient> findAll(Pageable pageable);

    Patient findOneById(Long id);

}
