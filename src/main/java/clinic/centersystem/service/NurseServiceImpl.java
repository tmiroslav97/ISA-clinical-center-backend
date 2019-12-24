package clinic.centersystem.service;

import clinic.centersystem.model.Nurse;
import clinic.centersystem.repository.NurseRepository;
import clinic.centersystem.service.intf.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public Nurse findById(Long id) {
        return this.nurseRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Nurse> findAll() {
        return this.nurseRepository.findAll();
    }

    @Override
    public List<Nurse> findAllOnClinic(Long clinicId) {
        return null;
    }

    @Override
    public Nurse save(Nurse nurse) {
        return this.nurseRepository.save(nurse);
    }
}
