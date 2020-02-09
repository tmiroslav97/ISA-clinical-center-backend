package clinic.centersystem.dto.request;

import lombok.*;
import org.joda.time.DateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomSearchDTO {

    private Long clinicId;

    private String name;

    private String date;

    private Integer pageCnt;

}
