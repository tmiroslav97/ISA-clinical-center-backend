package clinic.centersystem.repository;

import clinic.centersystem.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    boolean existsByName(String name);
}
