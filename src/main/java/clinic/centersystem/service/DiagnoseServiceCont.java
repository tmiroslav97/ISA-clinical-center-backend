package clinic.centersystem.service;

import clinic.centersystem.model.Diagnose;
import clinic.centersystem.service.intf.DiagnoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnoseServiceCont {

    @Autowired
    private DiagnoseService diagnoseService;

    public List<Diagnose> getDiagnoses() {
        return diagnoseService.findAll();
    }
}
