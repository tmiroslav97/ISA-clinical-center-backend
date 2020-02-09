package clinic.centersystem.service.intf;

import clinic.centersystem.dto.request.ClinicRequestDTO;
import clinic.centersystem.dto.request.ClinicSearchDTO;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.ClinicResponsePageDTO;
import clinic.centersystem.model.Clinic;

import java.util.List;

public interface ClinicService {
    Clinic findById(Long id);

    List<Clinic> findAll();

    Clinic save(ClinicRequestDTO clinicRequestDTO);

    Clinic saveClinic(Clinic clinic);

    boolean existsByName(String name);

    List<ClinicResponse> getClinics();


    ClinicResponse clinic(Long id);

    boolean registerClinic(ClinicRequestDTO clinicRequestDTO);

    ClinicResponsePageDTO findAll(Integer pageCnt);

    ClinicResponsePageDTO findAll(ClinicSearchDTO clinicSearchDTO);

}
