package clinic.centersystem.service;

import clinic.centersystem.converter.AbsenceRequirementConverter;
import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.AbsenceRequirementResponse;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.AbsenceHolidayRequirement;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.Personnel;
import clinic.centersystem.repository.AbsenceHolidayRequirementRepository;
import clinic.centersystem.service.intf.AbsenceHolidayRequirementService;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbsenceHolidayRequirementServiceImpl implements AbsenceHolidayRequirementService {

    @Autowired
    private AbsenceHolidayRequirementRepository absenceHolidayRequirementRepository;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PersonnelService personnelService;

    @Override
    public AbsenceHolidayRequirement findById(Long id) {
        return this.absenceHolidayRequirementRepository.findById(id).orElseThrow(()-> new ResourceNotExistsException("Absence or holiday doesn't exist"));
    }

    @Override
    public List<AbsenceHolidayRequirement> findAll() {
        return this.absenceHolidayRequirementRepository.findAll();
    }

    @Override
    public List<AbsenceRequirementResponse> findAllByPersonnelId(Long id) {
        return absenceHolidayRequirementRepository.findAllByPersonnelId(id).stream().map(AbsenceRequirementConverter::toCreateAbsenceRequirementResponseFromAbsenceRequirement).collect(Collectors.toList());
    }

    @Override
    public AbsenceHolidayRequirement save(AbsenceHolidayRequirement absenceHolidayRequirement) {
        return this.absenceHolidayRequirementRepository.save(absenceHolidayRequirement);
    }

    @Override
    public String submitAbsenceRequirement(AbsenceRequirementDTO absenceRequirementDTO) {
        AbsenceHolidayRequirement absenceHolidayRequirement = AbsenceRequirementConverter.toCreateAbsenceRequirementFromAbsenceRequest(absenceRequirementDTO);

        Integer count = absenceHolidayRequirementRepository.getCount(absenceHolidayRequirement.getStartDate(), absenceHolidayRequirement.getEndDate());
        if (count > 0) {
            return "Can't submit, intervals of requirements have intersection";
        }

        Personnel personnel = personnelService.findById(absenceRequirementDTO.getPersonnelId());
        Clinic clinic = clinicService.findById(absenceRequirementDTO.getClinicId());

        absenceHolidayRequirement.setClinic(clinic);
        absenceHolidayRequirement.setPersonnel(personnel);
        clinic.getReqAbs().add(absenceHolidayRequirement);
        personnel.getAbsenceHolidayRequirements().add(absenceHolidayRequirement);
        absenceHolidayRequirement = this.save(absenceHolidayRequirement);
        clinic = clinicService.saveClinic(clinic);
        personnel = personnelService.save(personnel);

        return "Successfully created absence requirement";
    }
}
