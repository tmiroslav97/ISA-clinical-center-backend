package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchRequestDTO {

    private Integer pageCnt;
    private Long clinicId;
    private Integer sort;
}
