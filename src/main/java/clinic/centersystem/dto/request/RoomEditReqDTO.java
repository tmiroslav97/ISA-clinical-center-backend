package clinic.centersystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomEditReqDTO {
    private Long id;

    private String name;

    private Integer roomNum;
}
