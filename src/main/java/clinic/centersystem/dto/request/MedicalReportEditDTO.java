package clinic.centersystem.dto.request;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReportEditDTO {
    private Long id;
    private String description;
    private Long diagnoseId;
    private Long doctorId;
}
