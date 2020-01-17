package clinic.centersystem.converter;

import clinic.centersystem.dto.response.RecepieResponse;
import clinic.centersystem.model.Prescription;

public class RecepieConverter {

    public static RecepieResponse toCreateRecepieResponseFromRecepie(Prescription prescription) {
        return RecepieResponse.builder()
                .id(prescription.getId())
                .build();
    }

}
