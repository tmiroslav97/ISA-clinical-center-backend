package clinic.centersystem.service.intf;

import clinic.centersystem.model.Calendar;

import java.util.List;

public interface CalendarService {

    Calendar findById(Long id);

    List<Calendar> findAll();

    Calendar save(Calendar calendar);
}
