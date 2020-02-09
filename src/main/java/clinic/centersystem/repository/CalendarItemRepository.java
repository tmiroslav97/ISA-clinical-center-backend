package clinic.centersystem.repository;

import clinic.centersystem.model.CalendarItem;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalendarItemRepository extends JpaRepository<CalendarItem, Long> {

    @Query("select count(ci) from CalendarItem  ci  where ci.calendar.id=(?1) and (ci.start<=(?3) and ci.end>=(?2))")
    Integer findByCalendarIdandDate(Long calId, DateTime dtStart, DateTime dtEnd);
}
