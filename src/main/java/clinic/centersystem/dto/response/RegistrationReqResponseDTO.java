package clinic.centersystem.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationReqResponseDTO {

    private List<RegistrationRequirementResponse> reqs = new ArrayList<>();

    private Integer regReqsPageCnt;
}
