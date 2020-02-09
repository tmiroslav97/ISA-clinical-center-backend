package clinic.centersystem.dto.response;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicResponsePageDTO {

    private List<ClinicResponse> clinics;

    private Integer pageCnt;
}
