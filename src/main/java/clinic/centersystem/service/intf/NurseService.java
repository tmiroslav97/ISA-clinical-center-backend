package clinic.centersystem.service.intf;

import clinic.centersystem.model.Nurse;

import java.util.List;

public interface NurseService {

    Nurse findById(Long id);

    List<Nurse> findAll();

    List<Nurse> findAllOnClinic(Long clinicId);

    Nurse save(Nurse nurse);

}
