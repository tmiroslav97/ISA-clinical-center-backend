package clinic.centersystem.converter;

import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.model.Nurse;

import java.util.stream.Collectors;

public class NurseConverter {

    public static NurseResponse toCreateNurseResponseFromNurse(Nurse nurse) {
        return NurseResponse.builder()
                .id(nurse.getId())
                .firstName(nurse.getFirstName())
                .lastName(nurse.getLastName())
                .email(nurse.getEmail())
                .roles(nurse.getAuthorities().stream().map(authority -> authority.getName()).collect(Collectors.toList()))
                .clinicId(nurse.getClinic().getId())
                .build();
    }
}
