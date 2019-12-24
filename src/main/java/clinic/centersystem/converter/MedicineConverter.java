package clinic.centersystem.converter;

import clinic.centersystem.dto.request.MedicineRequestDTO;
import clinic.centersystem.model.Medicine;

public class MedicineConverter {

    public static Medicine toCreateMedicineFromRequest(MedicineRequestDTO medicineRequestDTO) {
        return Medicine.builder()
                .name(medicineRequestDTO.getName())
                .code(medicineRequestDTO.getCode())
                .description(medicineRequestDTO.getDescription())
                .build();
    }

}
