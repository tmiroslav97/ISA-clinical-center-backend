package clinic.centersystem.repository;

import clinic.centersystem.model.ClinicAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin, Long> {
}
