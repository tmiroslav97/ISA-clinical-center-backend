package clinic.centersystem.authentication;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationRequest {
    
    private String username;
    private String password;

}
