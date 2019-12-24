package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String country;

    private String city;

    private String unoip;

    private String phoneNum;
}
