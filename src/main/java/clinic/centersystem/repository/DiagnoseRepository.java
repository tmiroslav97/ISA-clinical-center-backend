package clinic.centersystem.repository;

import clinic.centersystem.model.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}
