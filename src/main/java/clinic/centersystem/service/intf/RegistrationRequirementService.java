package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.model.RegistrationRequirement;

import java.util.List;

public interface RegistrationRequirementService {

    RegistrationRequirement findById(Long id);

    List<RegistrationRequirement> findAll();

    RegistrationRequirement save(RegistrationRequirementDTO registrationRequirement);

    void deleteById(Long id);
}
