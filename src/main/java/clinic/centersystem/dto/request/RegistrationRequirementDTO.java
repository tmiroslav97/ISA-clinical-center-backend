package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequirementDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String password2;

    private String address;

    private String country;

    private String city;

    private String phoneNum;

    private String unoip;
}
