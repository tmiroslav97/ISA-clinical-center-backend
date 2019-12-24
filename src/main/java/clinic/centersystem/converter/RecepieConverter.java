package clinic.centersystem.converter;

import clinic.centersystem.dto.response.RecepieResponse;
import clinic.centersystem.model.Recepie;

public class RecepieConverter {

    public static RecepieResponse toCreateRecepieResponseFromRecepie(Recepie recepie) {
        return RecepieResponse.builder()
                .id(recepie.getId())
                .build();
    }

}
