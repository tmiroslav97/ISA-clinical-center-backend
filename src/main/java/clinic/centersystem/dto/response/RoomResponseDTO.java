package clinic.centersystem.dto.response;

import clinic.centersystem.model.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private List<Room> rooms;

    private Integer pageCount;

}
