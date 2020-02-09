package clinic.centersystem.dto.response;

import clinic.centersystem.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseTerminDTO {

    private Room room;

    private String date;

    private List<Integer> termins;

    private String firstFreeTermin;
}
