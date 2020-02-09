package clinic.centersystem.converter;

import clinic.centersystem.dto.request.RoomReqDTO;
import clinic.centersystem.model.Room;

public class RoomRequestConverter {
    public  static Room toCreateFromRequest(RoomReqDTO roomReqDTO){
        return  Room.builder()
                .name(roomReqDTO.getName())
                .roomNum(roomReqDTO.getRoomNum())
                .type(roomReqDTO.getType())
                .build();
    }
}
