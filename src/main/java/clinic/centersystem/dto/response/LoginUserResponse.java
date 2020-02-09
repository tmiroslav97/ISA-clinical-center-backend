package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class LoginUserResponse {
    private Long id;

    private String email;

    private String token;

    private boolean firstLogin;

    private List<String> roles;

}
