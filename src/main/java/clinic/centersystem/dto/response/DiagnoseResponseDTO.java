package clinic.centersystem.dto.response;

import clinic.centersystem.model.Diagnose;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnoseResponseDTO {

    private List<Diagnose> diagnoses = new ArrayList<>();

    private Integer diagnosePageCnt;
}
