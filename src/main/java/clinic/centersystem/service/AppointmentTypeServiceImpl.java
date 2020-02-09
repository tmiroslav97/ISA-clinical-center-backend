package clinic.centersystem.service;

import clinic.centersystem.converter.AppointmentTypeConverter;
import clinic.centersystem.dto.request.AppointmentTypeEditReqDTO;
import clinic.centersystem.dto.request.AppointmentTypeRequestDTO;
import clinic.centersystem.dto.request.AppointmentTypeSearchReqDTO;
import clinic.centersystem.exception.ResourceNotExistsException;
import clinic.centersystem.model.AppointmentType;
import clinic.centersystem.model.Clinic;
import clinic.centersystem.repository.AppointmentTypeRepository;
import clinic.centersystem.service.intf.AppointmentTypeService;
import clinic.centersystem.service.intf.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    private ClinicService clinicService;

    @Override
    public AppointmentType findById(Long id){return this.appointmentTypeRepository.findById(id).orElseThrow(()-> new ResourceNotExistsException("Appointment type doesn't exist"));}

    @Override
    public List<AppointmentType> findAll(){return this.appointmentTypeRepository.findAll();}

    @Override
    public AppointmentType save(AppointmentType appointmentType){return this.appointmentTypeRepository.save(appointmentType);}

    @Override
    public void remove(Long id){this.appointmentTypeRepository.deleteById(id);}

    @Override
    public List<AppointmentType> getAppointmentType(Long clinicId){ return appointmentTypeRepository.findAllByClinicId(clinicId);}

    public String addAppointmentType(AppointmentTypeRequestDTO appointmentTypeRequestDTO, Long clinicId) {
        AppointmentType appointmentType = AppointmentTypeConverter.toCreateAppointmentTypeFromRequest(appointmentTypeRequestDTO);
        Clinic clinic = clinicService.findById(clinicId);
        appointmentType.setClinic(clinic);
        appointmentTypeRepository.save(appointmentType);

        return "Successfully added appointment type";
    }

    public String deleteAppointmentType(Long appointmentId){
        this.remove(appointmentId);
        return "Successfully deleted appointment type";
    }

    public String editAppointmentType(AppointmentTypeEditReqDTO appointmentTypeEditReqDTO){
        AppointmentType appointmentType = this.findById(appointmentTypeEditReqDTO.getId());
        appointmentType.setType(appointmentTypeEditReqDTO.getType());
        appointmentTypeRepository.save(appointmentType);
        return "Successfully edited appointment type";
    }

    public List<AppointmentType>searchAppointmentType(AppointmentTypeSearchReqDTO appointmentTypeSearchReqDTO){
        List<AppointmentType> appointmentType = appointmentTypeRepository.findByClinicIdAndTypeIgnoreCase(appointmentTypeSearchReqDTO.getClinicId(), appointmentTypeSearchReqDTO.getType());
        return appointmentType;
    }



}
