package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.model.ClinicCenterAdmin;

import java.util.List;

public interface ClinicCenterAdminService {

    ClinicCenterAdmin findById(Long id);

    List<ClinicCenterAdmin> findAll();

    ClinicCenterAdmin save(CCARegReqDTO ccaRegReqDTO);

}
