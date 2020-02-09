package clinic.centersystem.dto.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordChangerRequestDTO {

    private String oldPassword;
    private String newPassword;
    private String confNewPassword;
}
