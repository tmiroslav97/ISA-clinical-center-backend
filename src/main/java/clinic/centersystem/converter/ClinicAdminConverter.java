package clinic.centersystem.converter;

import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.response.ClinicAdministratoreResponse;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.ClinicAdmin;
import clinic.centersystem.model.ClinicCenterAdmin;
import clinic.centersystem.model.enumeration.RoleEnum;

public class ClinicAdminConverter {

    public static ClinicAdmin toCreateClinicAdminFromRequest(ClinicAdminReqDTO clinicAdminReqDTO) {
        return ClinicAdmin.clinicAdminBuilder()
                .email(clinicAdminReqDTO.getEmail())
                .firstName(clinicAdminReqDTO.getFirstName())
                .lastName(clinicAdminReqDTO.getLastName())
                .role(RoleEnum.ROLE_ADMINC)
                .password(clinicAdminReqDTO.getPassword())
                .build();
    }
    public static ClinicAdministratoreResponse toCreateClinicAdminResponse(ClinicAdmin clinicAdmin) {
        return ClinicAdministratoreResponse.builder()
                .id(clinicAdmin.getId())
                .email(clinicAdmin.getEmail())
                .firstName(clinicAdmin.getFirstName())
                .lastName(clinicAdmin.getLastName())
                .role(clinicAdmin.getRole().name())
                .build();
    }
}
