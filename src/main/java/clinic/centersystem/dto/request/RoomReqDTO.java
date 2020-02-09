package clinic.centersystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomReqDTO {
    private String name;

    private Integer roomNum;

    private String type;
}
