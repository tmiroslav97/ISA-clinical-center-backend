package clinic.centersystem.repository;

import clinic.centersystem.model.Room;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where lower(r.name) like lower(?1) and r.clinic.id=(?2)")
    Page<Room> searchRooms(String name, Long clinicId, Pageable pageable);

    Page<Room> findByClinicId(Long id, Pageable pageable);

    List<Room> findByClinicId(Long id);

    Boolean existsByRoomNum(Integer roomNum);

    List<Room> findAllByClinicIdAndType(Long id, String type);

    @Query("select r from Room r where lower(r.name) like lower(?1) and r.clinic.id=(?2)")
    List<Room> findByNameIgnoringCaseAndClinicId(String name, Long clinicId, Pageable pageable);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Room findOneById(Long id);
}
