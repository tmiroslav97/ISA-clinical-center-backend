package clinic.centersystem.dto.request;


import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineRequestDTO {

    private String name;

    private String code;

    private String description;

}
