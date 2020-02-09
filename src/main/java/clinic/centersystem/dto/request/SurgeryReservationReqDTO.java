package clinic.centersystem.dto.request;

import clinic.centersystem.dto.response.SurgeryRequirementDateResponseDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurgeryReservationReqDTO {

    private SurgeryRequirementDateResponseDTO pickedSurReq;

    private String pickedTerm;

    private Long pickedRoom;

    private List<Long> chosenDoc = new ArrayList<>();
}
