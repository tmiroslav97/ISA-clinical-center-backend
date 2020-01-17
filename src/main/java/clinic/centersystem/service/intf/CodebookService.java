package clinic.centersystem.service.intf;


import clinic.centersystem.model.Codebook;

import java.util.List;

public interface CodebookService {
    Codebook findById(Long id);

    List<Codebook> findAll();

    Codebook save(Codebook codebook);

    void add(Codebook codebook);

}
