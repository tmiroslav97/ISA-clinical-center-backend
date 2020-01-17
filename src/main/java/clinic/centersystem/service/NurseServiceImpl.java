package clinic.centersystem.service;

import clinic.centersystem.converter.NurseConverter;
import clinic.centersystem.converter.RecepieConverter;
import clinic.centersystem.dto.response.NurseResponse;
import clinic.centersystem.dto.response.RecepieResponse;
import clinic.centersystem.model.Nurse;
import clinic.centersystem.model.Prescription;
import clinic.centersystem.repository.NurseRepository;
import clinic.centersystem.service.intf.NurseService;
import clinic.centersystem.service.intf.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @Override
    public Nurse findById(Long id) {
        return this.nurseRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Nurse> findAll() {
        return this.nurseRepository.findAll();
    }

    @Override
    public List<Nurse> findAllOnClinic(Long clinicId) {
        return null;
    }

    @Override
    public Nurse save(Nurse nurse) {
        return this.nurseRepository.save(nurse);
    }

    @Override
    public NurseResponse getNurseById(Long id) {
        Nurse nurse = this.findById(id);
        NurseResponse nurseResponse = NurseConverter.toCreateNurseResponseFromNurse(nurse);
        return nurseResponse;
    }

    @Override
    public String rewriteRecepie(Long nurseId, Long recepieId) {
        Nurse nurse = this.findById(nurseId);
        Prescription prescription = prescriptionService.findById(recepieId);

        prescription.setValidate(true);
        nurse.getPrescriptions().add(prescription);
        prescription.setNurse(nurse);

        nurse = this.save(nurse);
        prescription = prescriptionService.save(prescription);

        return "Successfully rewrite recepie";
    }

    @Override
    public List<RecepieResponse> getRecepies() {
        List<Prescription> recepies = this.prescriptionService.findAll();
        List<RecepieResponse> recepieResponses = new ArrayList<RecepieResponse>();
        for (Prescription prescription : recepies) {
            if (!prescription.isValidate()) {
                recepieResponses.add(RecepieConverter.toCreateRecepieResponseFromRecepie(prescription));
            }
        }

        return recepieResponses;
    }
}
