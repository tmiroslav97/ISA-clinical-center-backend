package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.request.DoctorRequestDTO;
import clinic.centersystem.dto.response.ClinicAdministratoreResponse;
import clinic.centersystem.model.ClinicAdmin;

import java.util.List;

public interface ClinicAdminService {

    ClinicAdmin findById(Long id);

    List<ClinicAdmin> findAll();

    ClinicAdmin save(ClinicAdminReqDTO clinicAdminReqDTO);

    ClinicAdmin saveClinicAdmin(ClinicAdmin clinicAdmin);



}
