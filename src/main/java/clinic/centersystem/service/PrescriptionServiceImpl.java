package clinic.centersystem.service;

import clinic.centersystem.converter.PrescriptionConverter;
import clinic.centersystem.dto.response.PrescriptionResponse;
import clinic.centersystem.exception.AlreadyReweritedException;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Appointment;
import clinic.centersystem.model.MedicalReport;
import clinic.centersystem.model.Nurse;
import clinic.centersystem.model.Prescription;
import clinic.centersystem.model.enumeration.AppStateEnum;
import clinic.centersystem.repository.PrescriptionRepository;
import clinic.centersystem.service.intf.AppointmentService;
import clinic.centersystem.service.intf.MedicalReportService;
import clinic.centersystem.service.intf.NurseService;
import clinic.centersystem.service.intf.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private NurseService nurseService;

    @Autowired
    private AppointmentService appointmentService;

    @Override
    public Prescription findById(Long id) {
        return this.prescriptionRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Prescription doesn't exist"));
    }

    @Override
    public List<Prescription> findAll() {
        return this.prescriptionRepository.findAll();
    }

    @Override
    public Prescription save(Prescription prescription) {
        return this.prescriptionRepository.save(prescription);
    }

    @Override
    public List<PrescriptionResponse> findAllByClinicIdNotValidated(Long id) {
        return prescriptionRepository.findAllByClinicId(id).stream().map(PrescriptionConverter::toCreatePrescriptionResponseFromPrescription).collect(Collectors.toList());
    }

    @Override
    public List<Prescription> saveAll(List<Prescription> prescriptions) {
        return prescriptionRepository.saveAll(prescriptions);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String rewritePrescription(Long nurseId, Long prescriptionId) {
        Nurse nurse = nurseService.findById(nurseId);
        Prescription prescription = prescriptionService.findById(prescriptionId);
        if (prescription.isValidate()) {
            throw new AlreadyReweritedException("Prescription is already rewrited");
        }
        prescription.setValidate(true);
        nurse.getPrescriptions().add(prescription);
        prescription.setNurse(nurse);


//        otkomentarisati ako se zeli isprobati lock baze
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//        }


        nurse = nurseService.save(nurse);
        prescription = prescriptionService.save(prescription);
        MedicalReport medicalReport = prescription.getMedicalReport();
        boolean flag = false;
        for (Prescription pre : medicalReport.getPrescriptions()) {
            if (!pre.isValidate()) {
                flag = true;
            }
        }
        if (!flag) {
            Appointment appointment = medicalReport.getAppointment();
            appointment.setAppState(AppStateEnum.FINISHED);
            appointmentService.save(appointment);
        }
        return "Successfully rewrited prescription";
    }
}
