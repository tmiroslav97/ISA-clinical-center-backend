package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.MedicalRecordRequestDTO;
import clinic.centersystem.dto.request.MedicalReportEditDTO;
import clinic.centersystem.dto.request.MedicalReportRequestDTO;
import clinic.centersystem.dto.response.MedicalReportResponseDTO;
import clinic.centersystem.model.MedicalReport;

import java.util.List;

public interface MedicalReportService {

    MedicalReport findById(Long id);

    Integer editMedicalReport(MedicalReportEditDTO medicalReportEditDTO);

    List<MedicalReportResponseDTO> findDoctorReports(Long id);

    String addMedicalReport(MedicalReportRequestDTO medicalReportRequestDTO);
}
