package clinic.centersystem.service;

import clinic.centersystem.converter.AbsenceRequirementConverter;
import clinic.centersystem.converter.CalendarConverter;
import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.model.*;
import clinic.centersystem.service.intf.AbsenceRequirementService;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.PersonnelService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@NoArgsConstructor
public class PersonnelServiceCont {

    @Autowired
    private AbsenceRequirementService absenceRequirementService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private ClinicService clinicService;


    public String submitAbsenceRequirement(AbsenceRequirementDTO absenceRequirementDTO) {
        AbsenceRequirement absenceRequirement = AbsenceRequirementConverter.toCreateAbsenceRequirementFromAbsenceRequest(absenceRequirementDTO);
        Personnel personnel = this.personnelService.findById(absenceRequirementDTO.getPersonnelId());
        Clinic clinic = this.clinicService.findById(absenceRequirementDTO.getClinicId());

        absenceRequirement.setClinic(clinic);
        absenceRequirement.setPersonnel(personnel);
        clinic.getReqAbs().add(absenceRequirement);
        personnel.getAbsenceRequirements().add(absenceRequirement);
        absenceRequirement = this.absenceRequirementService.save(absenceRequirement);
        clinic = this.clinicService.saveClinic(clinic);
        personnel = this.personnelService.save(personnel);

        return "Successfullt created absence requirement";
    }

    public Set<AbsenceRequirement> getMyRequirements(Long personnelId) {
        Personnel personnel = this.personnelService.findById(personnelId);
        Set<AbsenceRequirement> absenceRequirements = personnel.getAbsenceRequirements();

        return absenceRequirements;
    }

    public CalendarResponse getMyCalendar(Long personnelId){
        Personnel personnel = this.personnelService.findById(personnelId);
        Calendar calendar = personnel.getCalendar();
        CalendarResponse calendarResponse = CalendarConverter.toCreateCalendarResponseFromCalendar(calendar);
        for(CalendarItem calendarItem : calendar.getCalendarItems()){
            calendarResponse.getCalendarItemResponses().add(CalendarConverter.toCreateCalendarItemResponseFromCalendarItem(calendarItem));
        }

        return  calendarResponse;
    }

}
