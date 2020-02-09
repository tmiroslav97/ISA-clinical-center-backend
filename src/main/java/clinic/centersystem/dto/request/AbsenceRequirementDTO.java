package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceRequirementDTO {

    private String type;

    private String startDate;

    private String endDate;

    private Long personnelId;

    private Long clinicId;

}
