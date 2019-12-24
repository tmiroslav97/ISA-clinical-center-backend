package clinic.centersystem.service;

import clinic.centersystem.model.Recepie;
import clinic.centersystem.repository.RecepieRepository;
import clinic.centersystem.service.intf.RecepieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecepieServiceImpl implements RecepieService {

    @Autowired
    private RecepieRepository recepieRepository;

    @Override
    public Recepie findById(Long id) {
        return this.recepieRepository.findById(id).orElseGet(null);
    }

    @Override
    public List<Recepie> findAll() {
        return this.recepieRepository.findAll();
    }

    @Override
    public Recepie save(Recepie recepie) {
        return this.recepieRepository.save(recepie);
    }
}
