package clinic.centersystem.converter;

import clinic.centersystem.dto.response.SurgeryRequirementDateResponseDTO;
import clinic.centersystem.model.SurgeryRequirement;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class SurgeryRequirementConverter {

    public static SurgeryRequirementDateResponseDTO fromSurReqToSurReqDTO(SurgeryRequirement surgeryRequirement){
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");

        return SurgeryRequirementDateResponseDTO.builder()
                .id(surgeryRequirement.getId())
                .date(dtf.print(surgeryRequirement.getDate()))
                .doctorId(surgeryRequirement.getDoctorId())
                .clinicId(surgeryRequirement.getClinic().getId())
                .doctorName(surgeryRequirement.getDoctorName())
                .patientId(surgeryRequirement.getPatientId())
                .patientName(surgeryRequirement.getPatientName())
                .termin(surgeryRequirement.getTermin())
                .build();
    }
}
