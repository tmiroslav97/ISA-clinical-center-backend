package clinic.centersystem.service;

import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Surgery;
import clinic.centersystem.repository.SurgeryRepository;
import clinic.centersystem.service.intf.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SurgeryServiceImpl implements SurgeryService {

    @Autowired
    SurgeryRepository surgeryRepository;

    @Override
    public Surgery findById(Long id) {
        return surgeryRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Surgery doesn't exist"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Surgery save(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }
}
