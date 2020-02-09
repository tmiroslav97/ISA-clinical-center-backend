package clinic.centersystem.dto.response;

import clinic.centersystem.model.Room;
import clinic.centersystem.model.SurgeryRequirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryRequirementResponseDTO {

    private List<SurgeryRequirementDateResponseDTO> surgeryRequirements;

    private Integer pageCount;
}
