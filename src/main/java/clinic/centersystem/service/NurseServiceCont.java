package clinic.centersystem.service;


import clinic.centersystem.converter.NurseConverter;
import clinic.centersystem.converter.RecepieConverter;
import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.dto.response.RecepieResponse;
import clinic.centersystem.model.Nurse;
import clinic.centersystem.model.Recepie;
import clinic.centersystem.service.intf.RecepieService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class NurseServiceCont {

    @Autowired
    private NurseServiceImpl nurseService;

    @Autowired
    private RecepieService recepieService;

    public NurseResponse getNurseById(Long id) {
        Nurse nurse = this.nurseService.findById(id);
        NurseResponse nurseResponse = NurseConverter.toCreateNurseResponseFromNurse(nurse);
        return nurseResponse;
    }

    public String rewriteRecepie(Long nurseId, Long recepieId) {
        Nurse nurse = nurseService.findById(nurseId);
        Recepie recepie = recepieService.findById(recepieId);

        recepie.setValidate(true);
        nurse.getRecepies().add(recepie);
        recepie.setNurse(nurse);

        nurse = nurseService.save(nurse);
        recepie = recepieService.save(recepie);

        return "Successfullt rewrite recepie";
    }

    public List<RecepieResponse> getRecepies() {
        List<Recepie> recepies = this.recepieService.findAll();
        List<RecepieResponse> recepieResponses = new ArrayList<RecepieResponse>();
        for (Recepie recepie : recepies) {
            if (!recepie.isValidate()) {
                recepieResponses.add(RecepieConverter.toCreateRecepieResponseFromRecepie(recepie));
            }
        }

        return recepieResponses;
    }

}
