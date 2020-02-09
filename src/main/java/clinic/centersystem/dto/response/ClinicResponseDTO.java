package clinic.centersystem.dto.response;

import clinic.centersystem.model.Clinic;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponseDTO {

    private List<Clinic> clinics;

    private Integer pageCount;

}
