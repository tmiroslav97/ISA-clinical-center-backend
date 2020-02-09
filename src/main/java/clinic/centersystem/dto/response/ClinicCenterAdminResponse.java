package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ClinicCenterAdminResponse {
    Long id;

    private String email;

    private boolean firstLogin;

    private List<String> roles;

    private boolean predefined;
}
