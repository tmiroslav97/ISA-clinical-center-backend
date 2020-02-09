package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicConverter;
import clinic.centersystem.dto.request.ClinicRequestDTO;
import clinic.centersystem.dto.request.ClinicSearchDTO;
import clinic.centersystem.dto.response.ClinicResponse;
import clinic.centersystem.dto.response.ClinicResponsePageDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.repository.ClinicRepository;
import clinic.centersystem.service.intf.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Clinic findById(Long id) {
        return this.clinicRepository.findById(id).orElseThrow(() -> new ResourceNotExistsException("Clinic doesn't exist"));
    }

    @Override
    public List<Clinic> findAll() {
        return this.clinicRepository.findAll();
    }

    @Override
    public Clinic save(ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = ClinicConverter.toCreateClinicFromRequest(clinicRequestDTO);
        clinic = this.clinicRepository.save(clinic);

        return clinic;
    }

    @Override
    public Clinic saveClinic(Clinic clinic) {
        clinic = this.clinicRepository.save(clinic);

        return clinic;
    }

    @Override
    public boolean existsByName(String name) {
        return clinicRepository.existsByName(name);
    }

    @Override
    public List<ClinicResponse> getClinics() {
        List<Clinic> clinics = this.clinicRepository.findAll();
        List<ClinicResponse> clinicResponses = new ArrayList<ClinicResponse>();
        for (Clinic clinic : clinics) {
            clinicResponses.add(ClinicConverter.toCreateClinicResponseFromClinic(clinic));
        }
        return clinicResponses;
    }

    @Override
    public boolean registerClinic(ClinicRequestDTO clinicRequestDTO) {
        if (this.clinicRepository.existsByName(clinicRequestDTO.getName())) {
            return false;
        }

        Clinic clinic = this.save(clinicRequestDTO);

        return true;
    }

    @Override
    public ClinicResponsePageDTO findAll(Integer pageCnt) {
        Pageable pageable = PageRequest.of(pageCnt, 10);
        Page<Clinic> clinics = clinicRepository.findAll(pageable);
        ClinicResponsePageDTO clinicResponsePageDTO = ClinicResponsePageDTO.builder()
                .clinics(clinics.getContent().stream().map(ClinicConverter::toCreateClinicResponseFromClinic).collect(Collectors.toList()))
                .pageCnt(clinics.getTotalPages())
                .build();
        return clinicResponsePageDTO;
    }

    @Override
    public ClinicResponse clinic(Long id) {
        Clinic clinic = this.findById(id);
        return ClinicConverter.toCreateClinicResponseFromClinic(clinic);
    }

    @Override
    public ClinicResponsePageDTO findAll(ClinicSearchDTO clinicSearchDTO) {
       Integer sort = clinicSearchDTO.getSort();
        Pageable pageable = null;
        if (sort == 0) {
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10);
        } else if (sort == 1) {
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10, Sort.by("name").ascending());
        }else if (sort == 2) {
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10, Sort.by("name").descending());
        }else if (sort == 3) {
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10, Sort.by("city").ascending());
        }else if (sort == 4) {
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10, Sort.by("city").descending());
        }else{
            pageable = PageRequest.of(clinicSearchDTO.getPageCnt(), 10);
        }
        Page<Clinic> clinics = clinicRepository.findAll(pageable);

        ClinicResponsePageDTO clinicResponsePageDTO = ClinicResponsePageDTO.builder()
                .clinics(clinics.getContent().stream().map(ClinicConverter::toCreateClinicResponseFromClinic).collect(Collectors.toList()))
                .pageCnt(clinics.getTotalPages())
                .build();
        return clinicResponsePageDTO;

    }

}
