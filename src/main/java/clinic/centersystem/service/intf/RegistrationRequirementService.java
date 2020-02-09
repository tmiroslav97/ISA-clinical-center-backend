package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.RegistrationRequirementDTO;
import clinic.centersystem.dto.response.RegistrationReqResponseDTO;
import clinic.centersystem.dto.response.RegistrationRequirementResponse;
import clinic.centersystem.model.RegistrationRequirement;

import java.util.List;

public interface RegistrationRequirementService {

    RegistrationRequirement findById(Long id);

    List<RegistrationRequirement> findAll();

    RegistrationRequirement save(RegistrationRequirementDTO registrationRequirement);

    List<RegistrationRequirementResponse> registrationRequirementList();

    String approveRegistrationRequest(Long id);

    int rejectRegistrationRequest(Long id, String message);

    RegistrationReqResponseDTO findAll(Integer pageCnt);

    void deleteById(Long id);
}
