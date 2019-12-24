package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password1;
    private String password2;
    private Integer startTime;
    private Integer endTime;
}
