package clinic.centersystem.service.intf;

import clinic.centersystem.model.Recepie;

import java.util.List;

public interface RecepieService {
    Recepie findById(Long id);

    List<Recepie> findAll();

    Recepie save(Recepie recepie);

}
