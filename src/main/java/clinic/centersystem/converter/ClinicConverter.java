package clinic.centersystem.converter;

import clinic.centersystem.dto.request.ClinicRequestDTO;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.model.Clinic;

public class ClinicConverter {

    public static Clinic toCreateClinicFromRequest(ClinicRequestDTO clinicRequestDTO) {
        return Clinic.builder()
                .name(clinicRequestDTO.getName())
                .description(clinicRequestDTO.getDescription())
                .address(clinicRequestDTO.getAddress())
                .sumRating(Float.valueOf(0))
                .cntRating(Float.valueOf(0))
                .build();
    }

    public static ClinicResponse toCreateClinicResponseFromClinic(Clinic clinic) {
        return ClinicResponse.builder()
                .id(clinic.getId())
                .name(clinic.getName())
                .description(clinic.getDescription())
                .address(clinic.getAddress())
                .build();
    }

}
