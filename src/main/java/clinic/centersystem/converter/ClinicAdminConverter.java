package clinic.centersystem.converter;

import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.dto.response.ClinicAdministratoreResponse;
import clinic.centersystem.model.ClinicAdmin;

import java.util.stream.Collectors;

public class ClinicAdminConverter {

    public static ClinicAdmin toCreateClinicAdminFromRequest(ClinicAdminReqDTO clinicAdminReqDTO) {
        return ClinicAdmin.clinicAdminBuilder()
                .email(clinicAdminReqDTO.getEmail())
                .firstName(clinicAdminReqDTO.getFirstName())
                .lastName(clinicAdminReqDTO.getLastName())
                .password(clinicAdminReqDTO.getPassword())
                .isFirstLog(true)
                .build();
    }

    public static ClinicAdministratoreResponse toCreateClinicAdminResponse(ClinicAdmin clinicAdmin) {
        return ClinicAdministratoreResponse.builder()
                .id(clinicAdmin.getId())
                .email(clinicAdmin.getEmail())
                .firstName(clinicAdmin.getFirstName())
                .lastName(clinicAdmin.getLastName())
                .roles(clinicAdmin.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList()))
                .clinicId(clinicAdmin.getClinic().getId())
                .build();
    }
}
