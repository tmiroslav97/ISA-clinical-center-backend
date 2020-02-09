package clinic.centersystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseTerminPageDTO {

    private List<RoomResponseTerminDTO> rooms;

    private Integer pageCount;
}
