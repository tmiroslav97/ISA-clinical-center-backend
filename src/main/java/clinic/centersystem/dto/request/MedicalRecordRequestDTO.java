package clinic.centersystem.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordRequestDTO {
    private Long id;
    private String bloodType;
    private String description;
    private Float weight;
    private Float height;
}
