package clinic.centersystem.service;

import clinic.centersystem.converter.AbsenceRequirementConverter;
import clinic.centersystem.converter.CalendarConverter;
import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.CalendarResponse;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.repository.PersonnelRepository;
import clinic.centersystem.service.intf.AbsenceHolidayRequirementService;
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
    private AbsenceHolidayRequirementService absenceHolidayRequirementService;

    @Autowired
    private ClinicService clinicService;

    @Override
    public Personnel findById(Long id) {
        return this.personnelRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Personnel doesn't exist"));
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
        AbsenceHolidayRequirement absenceHolidayRequirement = AbsenceRequirementConverter.toCreateAbsenceRequirementFromAbsenceRequest(absenceRequirementDTO);
        Personnel personnel = this.findById(absenceRequirementDTO.getPersonnelId());
        Clinic clinic = this.clinicService.findById(absenceRequirementDTO.getClinicId());

        absenceHolidayRequirement.setClinic(clinic);
        absenceHolidayRequirement.setPersonnel(personnel);
        clinic.getReqAbs().add(absenceHolidayRequirement);
        personnel.getAbsenceHolidayRequirements().add(absenceHolidayRequirement);
        absenceHolidayRequirement = this.absenceHolidayRequirementService.save(absenceHolidayRequirement);
        clinic = this.clinicService.saveClinic(clinic);
        personnel = this.save(personnel);

        return "Successfullt created absence requirement";
    }

    @Override
    public Set<AbsenceHolidayRequirement> getMyRequirements(Long personnelId) {
        Personnel personnel = this.findById(personnelId);
        Set<AbsenceHolidayRequirement> absenceHolidayRequirements = personnel.getAbsenceHolidayRequirements();

        return absenceHolidayRequirements;
    }

    @Override
    public CalendarResponse getMyCalendar(Long personnelId) {
        Personnel personnel = this.findById(personnelId);
        Calendar calendar = personnel.getCalendar();
        CalendarResponse calendarResponse = CalendarConverter.toCreateCalendarResponseFromCalendar(calendar);
        for (CalendarItem calendarItem : calendar.getCalendarItems()) {
            calendarResponse.getCalendarItemResponses().add(CalendarConverter.toCreateCalendarItemResponseFromCalendarItem(calendarItem));
        }

        return calendarResponse;
    }
}
