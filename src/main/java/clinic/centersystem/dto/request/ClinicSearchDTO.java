package clinic.centersystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicSearchDTO {

    private String date;

    private String type;

    private Float rating;

    private Integer pageCnt;

    private Integer sort;
}