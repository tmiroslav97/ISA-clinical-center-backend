package clinic.centersystem.service;

import clinic.centersystem.model.Personnel;
import clinic.centersystem.repository.PersonnelRepository;
import clinic.centersystem.service.intf.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Autowired
    private PersonnelRepository personnelRepository;

    @Override
    public Personnel findById(Long id) {
        return this.personnelRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Personnel> findAll() {
        return this.personnelRepository.findAll();
    }

    @Override
    public Personnel save(Personnel personnel) {
        return this.personnelRepository.save(personnel);
    }
}
