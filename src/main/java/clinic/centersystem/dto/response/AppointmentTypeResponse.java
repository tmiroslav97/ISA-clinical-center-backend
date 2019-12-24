package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentTypeResponse {
    private Long id;
    private String type;
    //private Long appointmentId;

    
}
