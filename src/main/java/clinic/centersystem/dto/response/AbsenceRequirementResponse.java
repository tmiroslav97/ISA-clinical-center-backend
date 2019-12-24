package clinic.centersystem.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceRequirementResponse {

    private String type;

    private String status;

    private Long startDate;

    private Long endDate;

}
