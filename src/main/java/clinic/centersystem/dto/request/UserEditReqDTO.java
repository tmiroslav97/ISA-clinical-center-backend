package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEditReqDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
