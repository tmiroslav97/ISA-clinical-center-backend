package clinic.centersystem.service;

import clinic.centersystem.converter.PrescriptionConverter;
import clinic.centersystem.dto.request.MedicalReportEditDTO;
import clinic.centersystem.dto.request.MedicalReportRequestDTO;
import clinic.centersystem.dto.response.MedicalReportResponseDTO;
import clinic.centersystem.exception.ResourceExistsException;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.*;
import clinic.centersystem.model.enumeration.AppStateEnum;
import clinic.centersystem.repository.MedicalReportRepository;
import clinic.centersystem.service.intf.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    @Autowired
    private MedicalReportRepository medicalReportRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private ClinicService clinicService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DiagnoseService diagnoseService;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public MedicalReport findById(Long id) {
        return medicalReportRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Medical report doesn't exist"));
    }

    @Override
    public Integer editMedicalReport(MedicalReportEditDTO medicalReportEditDTO) {
        MedicalReport medicalReport = this.findById(medicalReportEditDTO.getId());
        Doctor doctor = doctorService.findById(medicalReportEditDTO.getDoctorId());
        Appointment appointment = medicalReport.getAppointment();
        if(appointment.getDoctor().getId()!=doctor.getId()){
            return 1;
        }
        if(medicalReportEditDTO.getDiagnoseId()!=null){
            Diagnose diagnose = diagnoseService.findById(medicalReportEditDTO.getDiagnoseId());
            medicalReport.setDiagnose(diagnose);
        }
        medicalReport.setDescription(medicalReportEditDTO.getDescription());
        medicalReportRepository.save(medicalReport);

        return 2;
    }

    @Override
    public List<MedicalReportResponseDTO> findDoctorReports(Long id) {
        List<MedicalReportResponseDTO> medicalReportResponseDTO = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        List<Appointment> appointments = appointmentService.findAllByDoctorIdAndAppState(id, AppStateEnum.FINISHED);
        for (Appointment appointment : appointments) {
            MedicalReport medicalReport = medicalReportRepository.findByAppointmentId(appointment.getId());
            MedicalReportResponseDTO mRRDTO = MedicalReportResponseDTO.builder()
                    .id(medicalReport.getId())
                    .patientName(appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName())
                    .appDate(dtf.print(appointment.getDateTime()))
                    .description(medicalReport.getDescription())
                    .diagnoseName(medicalReport.getDiagnose().getName())
                    .medicineName(medicalReport.getPrescriptions().stream().map(pres -> pres.getMedicine().getName()).collect(Collectors.toList()))
                    .build();
            medicalReportResponseDTO.add(mRRDTO);
        }

        return medicalReportResponseDTO;
    }

    @Override
    public String addMedicalReport(MedicalReportRequestDTO medicalReportRequestDTO) {
        Appointment appointment = appointmentService.findById(medicalReportRequestDTO.getAppId());
        if (appointment.getAppState() == AppStateEnum.FINISHED || appointment.getAppState() == AppStateEnum.STARTED) {
            throw new ResourceExistsException("Appointment already finished");
        }
        Clinic clinic = clinicService.findById(appointment.getClinic().getId());
        Diagnose diagnose = diagnoseService.findById(medicalReportRequestDTO.getDiagnose());
        MedicalRecord medicalRecord = medicalRecordService.findById(medicalReportRequestDTO.getMedRecId());
        MedicalReport medicalReport = MedicalReport.builder()
                .appointment(appointment)
                .diagnose(diagnose)
                .medicalRecord(medicalRecord)
                .description(medicalReportRequestDTO.getDescription())
                .build();
        medicalReport = medicalReportRepository.save(medicalReport);

        MedicalReport medRep = medicalReport;

        if (medicalReportRequestDTO.getMedicines().size() > 0) {
            List<Medicine> medicines = medicineService.findAllByIdIn(medicalReportRequestDTO.getMedicines());
            List<Prescription> prescriptions = medicines.stream().map(med -> PrescriptionConverter.toCreatePrescriptionFromMedicine(med, clinic, medRep)).collect(Collectors.toList());
            prescriptions = prescriptionService.saveAll(prescriptions);
        } else {
            appointment.setAppState(AppStateEnum.FINISHED);
        }
        appointment.setAppState(AppStateEnum.STARTED);
        appointment.setMedicalReport(medicalReport);
        appointmentService.save(appointment);

        return "Appointment finished";
    }
}
