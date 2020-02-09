package clinic.centersystem.service;

import clinic.centersystem.converter.RoomRequestConverter;
import clinic.centersystem.dto.request.RoomEditReqDTO;
import clinic.centersystem.dto.request.RoomReqDTO;
import clinic.centersystem.dto.request.RoomSearchDTO;
import clinic.centersystem.dto.request.RoomSearchWithoutFiltratingReqDTO;
import clinic.centersystem.dto.response.RoomResponseDTO;
import clinic.centersystem.dto.response.RoomResponseTerminDTO;
import clinic.centersystem.dto.response.RoomResponseTerminPageDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.Room;
import clinic.centersystem.repository.RoomRepository;
import clinic.centersystem.service.intf.ClinicService;
import clinic.centersystem.service.intf.RoomCalendarService;
import clinic.centersystem.service.intf.RoomService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomCalendarService roomCalendarService;

    @Autowired
    private ClinicService clinicService;

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Room doesn't exist"));
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Room findOneById(Long id) {
        return roomRepository.findOneById(id);
    }

    @Override
    public RoomResponseDTO findByClinic(Long id, Integer pageCnt) {
        Pageable pageable = PageRequest.of(pageCnt, 10);
        Page<Room> rooms = roomRepository.findByClinicId(id, pageable);
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setRooms(rooms.getContent());
        roomResponseDTO.setPageCount(rooms.getTotalPages());

        return roomResponseDTO;
    }

    @Override
    public List<Room> findByClinicId(Long id) {
        return roomRepository.findByClinicId(id);
    }

    @Override
    public List<Room> findByClinicIdAndType(Long id, String type) {
        return roomRepository.findAllByClinicIdAndType(id, type);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public RoomResponseTerminPageDTO searchRooms(RoomSearchDTO roomSearchDTO) {
        Pageable pageable = PageRequest.of(roomSearchDTO.getPageCnt(), 10);
        DateTime dt = new DateTime(roomSearchDTO.getDate(), DateTimeZone.UTC);
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
        Page<Room> rooms = roomRepository.searchRooms(roomSearchDTO.getName(), roomSearchDTO.getClinicId(), pageable);
        RoomResponseTerminPageDTO roomResponseTerminPageDTO = new RoomResponseTerminPageDTO();
        List<RoomResponseTerminDTO> roomResponseTerminDTO = new ArrayList<>();
        roomResponseTerminPageDTO.setRooms(roomResponseTerminDTO);
        roomResponseTerminPageDTO.setPageCount(rooms.getTotalPages());

        DateTime now = new DateTime(LocalDate.now().toString(), DateTimeZone.UTC);
        for (Room room : rooms.getContent()) {
            RoomResponseTerminDTO rrtDTO = new RoomResponseTerminDTO();
            rrtDTO.setRoom(room);
            rrtDTO.setDate(dtf.print(dt));
            rrtDTO.setTermins(roomCalendarService.findByRoomAndDate(room.getId(), dt));
            roomResponseTerminDTO.add(rrtDTO);
            now = new DateTime(LocalDate.now().toString(), DateTimeZone.UTC);
            boolean flag = true;
            while (flag) {
                now = now.plusDays(1);
                List<Integer> termins = roomCalendarService.findByRoomAndDate(room.getId(), now);
                if (room.getType().equals("SUR")) {
                    for (int i = 7; i <= 16; i += 3) {
                        if (!termins.contains(i)) {
                            rrtDTO.setFirstFreeTermin(dtf.print(now) + " " + i + "-" + (i + 3));
                            flag = false;
                            break;
                        }
                    }
                } else {
                    for (int i = 7; i <= 18; i++) {
                        if (!termins.contains(i)) {
                            rrtDTO.setFirstFreeTermin(dtf.print(now) + " " + i + "-" + (i + 1));
                            flag = false;
                            break;
                        }
                    }
                }
            }

        }

        return roomResponseTerminPageDTO;
    }

    public List<Room>searchRoomsWithoutFiltrating(RoomSearchWithoutFiltratingReqDTO roomSearchWithoutFiltratingReqDTO){
        Pageable pageable = PageRequest.of(roomSearchWithoutFiltratingReqDTO.getPageCnt(), 10);
        List<Room> rooms = roomRepository.findByNameIgnoringCaseAndClinicId(roomSearchWithoutFiltratingReqDTO.getName(),roomSearchWithoutFiltratingReqDTO.getClinicId(), pageable);
        return rooms;
    }

    public String addRoom(RoomReqDTO roomReqDTO, Long clinicId){
        if(this.roomRepository.existsByRoomNum(roomReqDTO.getRoomNum())){
            return "Error, room number already used";
        }
        Room room = RoomRequestConverter.toCreateFromRequest(roomReqDTO);
        Clinic clinic = clinicService.findById(clinicId);
        room.setClinic(clinic);
        room.setReserved(false);
        roomRepository.save(room);
        return "Successfully added room";
    }

    public String editRoom(RoomEditReqDTO roomEditReqDTO){
        Room room = this.findById(roomEditReqDTO.getId());
        room.setName(roomEditReqDTO.getName());
        room.setRoomNum(roomEditReqDTO.getRoomNum());
        roomRepository.save(room);
        return "Successfully edited room";
    }

    public String deleteRoom(Long roomId){
        this.roomRepository.deleteById(roomId);
        return "Successfully deleted room";
    }

}
