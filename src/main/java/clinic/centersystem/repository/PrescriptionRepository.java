package clinic.centersystem.repository;

import clinic.centersystem.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("select pre from Prescription pre where pre.clinic.id=(?1) and pre.isValidate=FALSE")
    List<Prescription> findAllByClinicId(Long id);
}
