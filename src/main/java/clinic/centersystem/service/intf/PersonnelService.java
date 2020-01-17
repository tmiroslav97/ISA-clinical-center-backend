package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.AbsenceRequirement;
import clinic.centersystem.model.Personnel;

import java.util.List;
import java.util.Set;

public interface PersonnelService {
    Personnel findById(Long id);

    List<Personnel> findAll();

    Personnel save(Personnel personnel);

    String submitAbsenceRequirement(AbsenceRequirementDTO absenceRequirementDTO);

    Set<AbsenceRequirement> getMyRequirements(Long personnelId);

    CalendarResponse getMyCalendar(Long personnelId);
}