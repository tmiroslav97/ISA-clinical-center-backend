package clinic.centersystem.service.intf;

import clinic.centersystem.model.Personnel;

import java.util.List;

public interface PersonnelService {
    Personnel findById(Long id);

    List<Personnel> findAll();

    Personnel save(Personnel personnel);
}