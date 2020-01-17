package clinic.centersystem.service;

import clinic.centersystem.converter.AbsenceRequirementConverter;
import clinic.centersystem.converter.CalendarConverter;
import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.PersonnelRepository;
import clinic.centersystem.service.intf.AbsenceRequirementService;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private AbsenceRequirementService absenceRequirementService;

    @Autowired
    private ClinicService clinicService;

    @Override
    public Personnel findById(Long id) {
        return this.personnelRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Personnel> findAll() {
        return this.personnelRepository.findAll();
    }

    @Override
    public Personnel save(Personnel personnel) {
        return this.personnelRepository.save(personnel);
    }

    @Override
    public String submitAbsenceRequirement(AbsenceRequirementDTO absenceRequirementDTO) {
        AbsenceRequirement absenceRequirement = AbsenceRequirementConverter.toCreateAbsenceRequirementFromAbsenceRequest(absenceRequirementDTO);
        Personnel personnel = this.findById(absenceRequirementDTO.getPersonnelId());
        Clinic clinic = this.clinicService.findById(absenceRequirementDTO.getClinicId());

        absenceRequirement.setClinic(clinic);
        absenceRequirement.setPersonnel(personnel);
        clinic.getReqAbs().add(absenceRequirement);
        personnel.getAbsenceRequirements().add(absenceRequirement);
        absenceRequirement = this.absenceRequirementService.save(absenceRequirement);
        clinic = this.clinicService.saveClinic(clinic);
        personnel = this.save(personnel);

        return "Successfullt created absence requirement";
    }

    @Override
    public Set<AbsenceRequirement> getMyRequirements(Long personnelId) {
        Personnel personnel = this.findById(personnelId);
        Set<AbsenceRequirement> absenceRequirements = personnel.getAbsenceRequirements();

        return absenceRequirements;
    }

    @Override
    public CalendarResponse getMyCalendar(Long personnelId){
        Personnel personnel = this.findById(personnelId);
        Calendar calendar = personnel.getCalendar();
        CalendarResponse calendarResponse = CalendarConverter.toCreateCalendarResponseFromCalendar(calendar);
        for(CalendarItem calendarItem : calendar.getCalendarItems()){
            calendarResponse.getCalendarItemResponses().add(CalendarConverter.toCreateCalendarItemResponseFromCalendarItem(calendarItem));
        }

        return  calendarResponse;
    }
}
