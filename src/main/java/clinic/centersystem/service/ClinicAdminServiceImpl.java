package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicAdminConverter;
import clinic.centersystem.dto.request.ClinicAdminReqDTO;
import clinic.centersystem.model.ClinicAdmin;
import clinic.centersystem.repository.ClinicAdminRepository;
import clinic.centersystem.service.intf.ClinicAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  ClinicAdminServiceImpl implements ClinicAdminService {

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;

    @Override
    public ClinicAdmin findById(Long id) {

        return this.clinicAdminRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<ClinicAdmin> findAll() {

        return this.clinicAdminRepository.findAll();
    }

    @Override
    public ClinicAdmin save(ClinicAdminReqDTO clinicAdminReqDTO) {
        ClinicAdmin clinicAdmin = ClinicAdminConverter.toCreateClinicAdminFromRequest(clinicAdminReqDTO);

        clinicAdmin = this.clinicAdminRepository.save(clinicAdmin);

        return clinicAdmin;
    }

    @Override
    public ClinicAdmin saveClinicAdmin(ClinicAdmin clinicAdmin) {
        clinicAdmin = this.clinicAdminRepository.save(clinicAdmin);

        return clinicAdmin;
    }
}
