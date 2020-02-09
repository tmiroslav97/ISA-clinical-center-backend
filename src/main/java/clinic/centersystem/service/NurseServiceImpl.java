package clinic.centersystem.service;

import clinic.centersystem.converter.NurseConverter;
import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Nurse;
import clinic.centersystem.repository.NurseRepository;
import clinic.centersystem.service.intf.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public Nurse findById(Long id) {
        return this.nurseRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Nurse doesn't exist"));
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
    @Transactional(propagation = Propagation.REQUIRED)
    public Nurse save(Nurse nurse) {
        return this.nurseRepository.save(nurse);
    }

    @Override
    public NurseResponse getNurseById(Long id) {
        Nurse nurse = this.findById(id);
        NurseResponse nurseResponse = NurseConverter.toCreateNurseResponseFromNurse(nurse);
        return nurseResponse;
    }


}
