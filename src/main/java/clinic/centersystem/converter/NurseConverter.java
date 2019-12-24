package clinic.centersystem.converter;

import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.model.Nurse;

public class NurseConverter {

    public static NurseResponse toCreateNurseResponseFromNurse(Nurse nurse) {
        return NurseResponse.builder()
                .id(nurse.getId())
                .firstName(nurse.getFirstName())
                .lastName(nurse.getLastName())
                .email(nurse.getEmail())
                .role(nurse.getRole().name())
                .clinicId(nurse.getClinic().getId())
                .build();
    }
}
