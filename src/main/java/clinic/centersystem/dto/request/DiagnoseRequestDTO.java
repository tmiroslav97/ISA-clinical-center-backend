package clinic.centersystem.dto.request;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiagnoseRequestDTO {
    
    private String name;

    private String code;

    private String description;

}
