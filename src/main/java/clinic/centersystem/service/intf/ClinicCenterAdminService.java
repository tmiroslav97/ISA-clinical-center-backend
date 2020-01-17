package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.request.ClinicRequestDTO;
import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.RegistrationRequirementResponse;
import clinic.centersystem.model.ClinicCenterAdmin;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ClinicCenterAdminService {

    ClinicCenterAdmin findById(Long id);

    List<ClinicCenterAdmin> findAll();

    ClinicCenterAdmin save(CCARegReqDTO ccaRegReqDTO);

    ClinicCenterAdminResponse clinicCenterAdmin(Long id);

    List<RegistrationRequirementResponse> registrationRequirementList();

    String approveRegistrationRequest(Long id);

    String rejectRegistrationRequest(Long id, String message);

    String registerCCA(CCARegReqDTO ccaRegReqDTO, Long id);

    String activateAccount(Long id, HttpServletResponse httpServletResponse);

    boolean registerClinic(ClinicRequestDTO clinicRequestDTO);

    List<ClinicResponse> getClinics();

    String registerClinicAdmin(ClinicAdminReqDTO clinicAdminReqDTO);


}
