package clinic.centersystem.dto.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    private String appState;
    private String patient;
}
