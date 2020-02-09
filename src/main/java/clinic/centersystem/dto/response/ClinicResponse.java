package clinic.centersystem.dto.response;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponse {

    private Long id;

    private String name;

    private String description;

    private String address;


}
