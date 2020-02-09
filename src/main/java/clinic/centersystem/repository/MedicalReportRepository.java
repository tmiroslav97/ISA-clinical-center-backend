package clinic.centersystem.repository;

import clinic.centersystem.model.MedicalReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {

    MedicalReport findByAppointmentId(Long id);
}
