package clinic.centersystem.repository;

import clinic.centersystem.model.RegistrationRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRequirementRepository extends JpaRepository<RegistrationRequirement, Long> {

    Page<RegistrationRequirement> findAll(Pageable pageable);

}
