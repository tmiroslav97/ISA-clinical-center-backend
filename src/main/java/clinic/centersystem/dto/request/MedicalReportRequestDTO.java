package clinic.centersystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalReportRequestDTO {
    private Long appId;
    private Long medRecId;
    private String description;
    private Set<Long> medicines;
    private Long diagnose;
}
