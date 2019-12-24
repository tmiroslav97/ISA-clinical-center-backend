package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.DiagnoseRequestDTO;
import clinic.centersystem.model.Diagnose;

import java.util.List;

public interface DiagnoseService {

    Diagnose findById(Long id);

    List<Diagnose> findAll();

    Diagnose save(DiagnoseRequestDTO diagnoseRequestDTO);

}
