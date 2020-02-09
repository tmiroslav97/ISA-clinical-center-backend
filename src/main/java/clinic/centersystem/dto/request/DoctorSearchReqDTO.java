package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSearchReqDTO {
    private String lastName;

    private  Long clinicId;
}
