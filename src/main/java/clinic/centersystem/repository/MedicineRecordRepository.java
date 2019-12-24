package clinic.centersystem.repository;

import clinic.centersystem.model.MedicineRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRecordRepository extends JpaRepository<MedicineRecord, Long> {
}
