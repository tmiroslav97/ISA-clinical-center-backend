package clinic.centersystem.service;

import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.RoomCalendar;
import clinic.centersystem.repository.RoomCalendarRepository;
import clinic.centersystem.service.intf.RoomCalendarService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomCalendarServiceImpl implements RoomCalendarService {

    @Autowired
    private RoomCalendarRepository roomCalendarRepository;

    @Override
    public RoomCalendar findById(Long id) {
        return roomCalendarRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Room calendar doesn't exist"));
    }

    @Override
    public List<Integer> findByRoomAndDate(Long roomId, DateTime dt) {
        return roomCalendarRepository.findByRoomAndDate(roomId, dt);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoomCalendar save(RoomCalendar roomCalendar) {
        return roomCalendarRepository.save(roomCalendar);
    }

}
