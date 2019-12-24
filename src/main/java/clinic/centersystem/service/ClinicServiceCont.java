package clinic.centersystem.service;


import clinic.centersystem.converter.ClinicConverter;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.service.intf.ClinicService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
public class ClinicServiceCont {

    @Autowired
    private ClinicService clinicService;

    public List<ClinicResponse> getClinics() {
        List<Clinic> clinics = this.clinicService.findAll();
        List<ClinicResponse> clinicResponses = new ArrayList<ClinicResponse>();
        for(Clinic clinic : clinics) {
            clinicResponses.add(ClinicConverter.toCreateClinicResponseFromClinic(clinic));
        }

    return clinicResponses;
    }
}
