package clinic.centersystem.service;

import clinic.centersystem.model.AppointmentType;
import clinic.centersystem.repository.AppointmentTypeRepository;
import clinic.centersystem.service.intf.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {

    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    @Override
    public AppointmentType findById(Long id){return this.appointmentTypeRepository.findById(id).orElseGet(null);}

    @Override
    public List<AppointmentType> findAll(){return this.appointmentTypeRepository.findAll();}

    @Override
    public AppointmentType save(AppointmentType appointmentType){return this.appointmentTypeRepository.save(appointmentType);}

    public void remove(Long id){this.appointmentTypeRepository.deleteById(id);}

}
