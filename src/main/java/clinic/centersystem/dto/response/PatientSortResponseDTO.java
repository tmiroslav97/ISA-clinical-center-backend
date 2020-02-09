package clinic.centersystem.dto.response;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientSortResponseDTO {
    private List<PatientResponse> patients;
    private Integer patientPageCnt;
}
