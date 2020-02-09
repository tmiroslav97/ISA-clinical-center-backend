package clinic.centersystem.repository;

import clinic.centersystem.model.SurgeryRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRequirementRepository extends JpaRepository<SurgeryRequirement, Long> {

    Page<SurgeryRequirement> findByClinicId(Long id, Pageable pageable);

}
