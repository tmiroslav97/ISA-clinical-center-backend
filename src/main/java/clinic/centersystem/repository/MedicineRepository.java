package clinic.centersystem.repository;

import clinic.centersystem.model.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Page<Medicine> findAll(Pageable pageable);

    List<Medicine> findAllByIdIn(Set<Long> medicines);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}
