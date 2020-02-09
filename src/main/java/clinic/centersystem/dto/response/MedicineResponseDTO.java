package clinic.centersystem.dto.response;

import clinic.centersystem.model.Medicine;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicineResponseDTO {

    private List<Medicine> medicines = new ArrayList<>();

    private Integer medicinePageCnt;
}
