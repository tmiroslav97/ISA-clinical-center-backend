package clinic.centersystem.repository;

import clinic.centersystem.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query("select c.id  from  Calendar c where c.personnel.id=(?1)")
    Long findCalendarIdByPersonnelId(Long id);

    Calendar findOneById(Long id);
}
