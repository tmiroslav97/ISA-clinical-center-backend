package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentTypeSearchReqDTO {
    private Long clinicId;
    private String type;
}
