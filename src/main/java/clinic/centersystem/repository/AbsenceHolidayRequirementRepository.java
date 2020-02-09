package clinic.centersystem.repository;

import clinic.centersystem.model.AbsenceHolidayRequirement;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceHolidayRequirementRepository extends JpaRepository<AbsenceHolidayRequirement, Long> {

    List<AbsenceHolidayRequirement> findAllByPersonnelId(Long id);

    @Query("select count(abshol) from AbsenceHolidayRequirement abshol where (abshol.startDate<=(?2) and abshol.endDate>=(?1))")
    Integer getCount(DateTime dtStart, DateTime dtEnd);
}
