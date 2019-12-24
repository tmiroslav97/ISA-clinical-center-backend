package clinic.centersystem.service.intf;

import clinic.centersystem.model.AbsenceRequirement;

import java.util.List;

public interface AbsenceRequirementService {
    AbsenceRequirement findById(Long id);

    List<AbsenceRequirement> findAll();

    AbsenceRequirement save(AbsenceRequirement absenceRequirement);
}
