package clinic.centersystem.repository;

import clinic.centersystem.model.Diagnose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

    Page<Diagnose> findAll(Pageable pageable);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
