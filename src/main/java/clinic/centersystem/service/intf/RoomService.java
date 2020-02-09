package clinic.centersystem.service.intf;


import clinic.centersystem.dto.request.RoomSearchDTO;
import clinic.centersystem.dto.response.RoomResponseDTO;
import clinic.centersystem.dto.response.RoomResponseTerminPageDTO;
import clinic.centersystem.model.Room;

import java.util.List;

public interface RoomService {

    Room findById(Long id);

    Room findOneById(Long id);

    RoomResponseDTO findByClinic(Long id, Integer pageCnt);

    List<Room> findByClinicId(Long id);

    List<Room> findByClinicIdAndType(Long id, String type);

    List<Room> findAll();

    Room save(Room room);

    RoomResponseTerminPageDTO searchRooms(RoomSearchDTO roomSearchDTO);
}
