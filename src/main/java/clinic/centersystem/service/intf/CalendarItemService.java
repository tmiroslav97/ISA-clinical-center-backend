package clinic.centersystem.service.intf;

import clinic.centersystem.model.CalendarItem;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface CalendarItemService {

    CalendarItem findById(Long id);

    List<CalendarItem> findAll();

    CalendarItem save(CalendarItem calendarItem);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Integer findByCalendarIdandDate(Long calId, DateTime dtStart, DateTime dtEnd);
}
