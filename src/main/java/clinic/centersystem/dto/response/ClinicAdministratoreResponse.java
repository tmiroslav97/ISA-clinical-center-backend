package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ClinicAdministratoreResponse {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String role;
}
