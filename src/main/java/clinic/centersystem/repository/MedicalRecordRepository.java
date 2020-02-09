package clinic.centersystem.repository;

import clinic.centersystem.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    MedicalRecord findByPatientId(Long id);

    @Query("select mr from MedicalRecord mr, Appointment app where app.id=(?1) and mr.patient=app.patient")
    MedicalRecord findByAppId(Long id);
}
