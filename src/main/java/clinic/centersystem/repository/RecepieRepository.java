package clinic.centersystem.repository;

import clinic.centersystem.model.Recepie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecepieRepository extends JpaRepository<Recepie, Long> {
}
