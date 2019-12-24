package clinic.centersystem.service;

import clinic.centersystem.converter.ClinicCenterAdminConverter;
import clinic.centersystem.dto.request.CCARegReqDTO;
import clinic.centersystem.exception.UserNotFoundException;
import clinic.centersystem.model.Authority;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.model.ClinicCenterAdmin;
import clinic.centersystem.repository.ClinicCenterAdminRepository;
import clinic.centersystem.service.intf.AuthorityService;
import clinic.centersystem.service.intf.ClinicCenterAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicCenterAdminServiceImpl implements ClinicCenterAdminService {

    @Autowired
    private ClinicCenterAdminRepository clinicCenterAdminRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public ClinicCenterAdmin findById(Long id) {
        return this.clinicCenterAdminRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<ClinicCenterAdmin> findAll() {
        return null;
    }

    @Override
    public ClinicCenterAdmin save(CCARegReqDTO ccaRegReqDTO) {
        ClinicCenterAdmin clinicCenterAdmin = ClinicCenterAdminConverter.toCreateClinicCenterAdmin(ccaRegReqDTO);
        List<Authority> auths = this.authorityService.findByName("ROLE_CCADMIN");
        clinicCenterAdmin.setAuthorities(auths);
        clinicCenterAdmin = this.clinicCenterAdminRepository.save(clinicCenterAdmin);
        return clinicCenterAdmin;
    }
}
