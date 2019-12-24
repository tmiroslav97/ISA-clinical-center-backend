package clinic.centersystem.converter;

import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.model.ClinicCenterAdmin;
import clinic.centersystem.model.enumeration.RoleEnum;

public class ClinicCenterAdminConverter {

    public static ClinicCenterAdminResponse toCreateClinicCenterAdminResponse(ClinicCenterAdmin clinicCenterAdmin) {
        return ClinicCenterAdminResponse.builder()
                .id(clinicCenterAdmin.getId())
                .email(clinicCenterAdmin.getEmail())
                .role(clinicCenterAdmin.getRole().name())
                .isNotFirstLogin(clinicCenterAdmin.isFirstLog())
                .predefined(clinicCenterAdmin.isPredefined())
                .build();
    }

    public static ClinicCenterAdmin toCreateClinicCenterAdmin(CCARegReqDTO ccaRegReqDTO) {
        return ClinicCenterAdmin.clinicCenterAdminBuilder()
                .email(ccaRegReqDTO.getEmail())
                .firstName(ccaRegReqDTO.getFirstName())
                .lastName(ccaRegReqDTO.getLastName())
                .isFirstLog(true)
                .role(RoleEnum.ROLE_CCADMIN)
                .enabled(true)
                .password(ccaRegReqDTO.getPassword())
                .predefined(false)
                .build();

    }
}
