package clinic.centersystem.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryRequirementDateResponseDTO {

    private Long id;

    private String date;

    private Integer termin;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private Long clinicId;
}

