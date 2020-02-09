package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTypeEditReqDTO {
    private Long id;
    private String type;
}
