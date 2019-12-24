package clinic.centersystem.service;

import clinic.centersystem.model.AbsenceRequirement;
import clinic.centersystem.repository.AbsenceRequirementRepository;
import clinic.centersystem.service.intf.AbsenceRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbsenceRequirementServiceImpl implements AbsenceRequirementService {

    @Autowired
    private AbsenceRequirementRepository absenceRequirementRepository;

    @Override
    public AbsenceRequirement findById(Long id) {
        return this.absenceRequirementRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<AbsenceRequirement> findAll() {
        return this.absenceRequirementRepository.findAll();
    }

    @Override
    public AbsenceRequirement save(AbsenceRequirement absenceRequirement) {
        return this.absenceRequirementRepository.save(absenceRequirement);
    }
}
