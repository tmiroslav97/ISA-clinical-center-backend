package clinic.centersystem.converter;

import clinic.centersystem.dto.request.DiagnoseRequestDTO;
import clinic.centersystem.model.Diagnose;

public class DiagnoseConverter {

    public static Diagnose toCreateDiagnoseFromRequest(DiagnoseRequestDTO diagnoseRequestDTO){
        return Diagnose.builder()
                .name(diagnoseRequestDTO.getName())
                .code(diagnoseRequestDTO.getCode())
                .description(diagnoseRequestDTO.getDescription())
                .build();
    }
}
