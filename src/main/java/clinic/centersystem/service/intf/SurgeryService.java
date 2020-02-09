package clinic.centersystem.service.intf;

import clinic.centersystem.model.Surgery;

public interface SurgeryService {

    Surgery findById(Long id);

    Surgery save(Surgery surgery);
}
