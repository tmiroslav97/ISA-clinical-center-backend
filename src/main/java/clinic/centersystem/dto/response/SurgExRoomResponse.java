package clinic.centersystem.dto.response;

import clinic.centersystem.model.Clinic;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurgExRoomResponse {
    private Long id;
    private Integer number;
    private String name;
    private Clinic clinic;
}
