package clinic.centersystem.repository;

import clinic.centersystem.model.Codebook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodebookRepository extends JpaRepository<Codebook, Long> {
}
