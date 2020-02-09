package clinic.centersystem.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReportResponseDTO {
    private Long id;
    private String patientName;
    private String appDate;
    private String description;
    private String diagnoseName;
    private List<String> medicineName;
}
