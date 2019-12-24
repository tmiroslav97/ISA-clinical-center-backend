package clinic.centersystem.repository;

import clinic.centersystem.model.CalendarItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarItemRepository extends JpaRepository<CalendarItem, Long> {
}
