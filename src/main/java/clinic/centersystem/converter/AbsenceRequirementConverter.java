package clinic.centersystem.converter;

import clinic.centersystem.dto.request.AbsenceRequirementDTO;
import clinic.centersystem.dto.response.AbsenceRequirementResponse;
import clinic.centersystem.model.AbsenceHolidayRequirement;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class AbsenceRequirementConverter {

    public static AbsenceHolidayRequirement toCreateAbsenceRequirementFromAbsenceRequest(AbsenceRequirementDTO absenceRequirementDTO) {
        DateTime startDate = new DateTime(absenceRequirementDTO.getStartDate(), DateTimeZone.UTC);
        DateTime endDate = new DateTime(absenceRequirementDTO.getEndDate(), DateTimeZone.UTC);
        return AbsenceHolidayRequirement.builder()
                .type(absenceRequirementDTO.getType())
                .startDate(startDate)
                .endDate(endDate)
                .status("REQUESTED")
                .build();
    }

    public static AbsenceRequirementResponse toCreateAbsenceRequirementResponseFromAbsenceRequirement(AbsenceHolidayRequirement absenceHolidayRequirement) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        String startDate = dtf.print(absenceHolidayRequirement.getStartDate());
        String endDate = dtf.print(absenceHolidayRequirement.getEndDate());
        return AbsenceRequirementResponse.builder()
                .type(absenceHolidayRequirement.getType())
                .status(absenceHolidayRequirement.getStatus())
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
