package clinic.centersystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchWithoutFiltratingReqDTO {
    private Long clinicId;

    private String name;

    private Integer pageCnt;
}
