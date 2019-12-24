package clinic.centersystem.service.intf;

import clinic.centersystem.model.CalendarItem;

import java.util.List;

public interface CalendarItemService {

    CalendarItem findById(Long id);

    List<CalendarItem> findAll();

    CalendarItem save(CalendarItem calendarItem);
}
