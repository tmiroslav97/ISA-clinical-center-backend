package clinic.centersystem.repository;

import clinic.centersystem.model.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    boolean existsByName(String name);

    @Query("select cl from Clinic cl, AppointmentType at where at.type=(?3) and cl=at.clinic")
    List<Clinic> findAll(String date, Float rating, String type);

    Page<Clinic> findAll(Pageable pageable);
}
