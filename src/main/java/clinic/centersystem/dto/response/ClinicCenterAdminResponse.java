package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ClinicCenterAdminResponse {
    Long id;

    private String email;

    private boolean isNotFirstLogin;

    private String role;

    private boolean predefined;
}
