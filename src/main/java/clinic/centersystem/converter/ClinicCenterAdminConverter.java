package clinic.centersystem.converter;

import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.dto.response.ClinicCenterAdminResponse;
import clinic.centersystem.model.ClinicCenterAdmin;
import clinic.centersystem.model.enumeration.RoleEnum;

import java.util.stream.Collectors;

public class ClinicCenterAdminConverter {

    public static ClinicCenterAdminResponse toCreateClinicCenterAdminResponse(ClinicCenterAdmin clinicCenterAdmin) {
        return ClinicCenterAdminResponse.builder()
                .id(clinicCenterAdmin.getId())
                .email(clinicCenterAdmin.getEmail())
                .roles(clinicCenterAdmin.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList()))
                .firstLogin(clinicCenterAdmin.isFirstLog())
                .predefined(clinicCenterAdmin.isPredefined())
                .build();
    }

    public static ClinicCenterAdmin toCreateClinicCenterAdmin(CCARegReqDTO ccaRegReqDTO) {
        return ClinicCenterAdmin.clinicCenterAdminBuilder()
                .email(ccaRegReqDTO.getEmail())
                .firstName(ccaRegReqDTO.getFirstName())
                .lastName(ccaRegReqDTO.getLastName())
                .isFirstLog(true)
                .enabled(true)
                .password(ccaRegReqDTO.getPassword())
                .predefined(false)
                .build();

    }
}
