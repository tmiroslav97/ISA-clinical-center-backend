package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NurseResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

    private Long clinicId;
}
