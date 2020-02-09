package clinic.centersystem.service;

import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Calendar;
import clinic.centersystem.repository.CalendarRepository;
import clinic.centersystem.service.intf.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public Calendar findById(Long id) {
        return this.calendarRepository.findById(id).orElseThrow(()-> new ResourceNotExistsException("Calendar doesn't exist"));
    }

    @Override
    public List<Calendar> findAll() {
        return this.calendarRepository.findAll();
    }

    @Override
    public Calendar save(Calendar calendar) {
        return this.calendarRepository.save(calendar);
    }

    @Override
    public Long findCalendarIdByPersonnelId(Long id) {
        return calendarRepository.findCalendarIdByPersonnelId(id);
    }

    @Override
    public Calendar findOneById(Long id) {
        return calendarRepository.findOneById(id);
    }


}
