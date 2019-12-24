package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CCARegReqDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
