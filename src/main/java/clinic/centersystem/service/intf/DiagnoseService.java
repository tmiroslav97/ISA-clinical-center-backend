package clinic.centersystem.service.intf;

import clinic.centersystem.dto.response.DiagnoseResponseDTO;
import clinic.centersystem.model.Diagnose;

import java.util.List;

public interface DiagnoseService {
    Diagnose findById(Long id);

    List<Diagnose> findAll();

    DiagnoseResponseDTO findAll(Integer pageCnt);

    Diagnose save(Diagnose diagnose);
}
