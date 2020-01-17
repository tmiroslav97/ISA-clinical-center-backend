package clinic.centersystem.service;

import clinic.centersystem.exception.CodebookExistsException;
import clinic.centersystem.model.Codebook;
import clinic.centersystem.repository.CodebookRepository;
import clinic.centersystem.service.intf.CodebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodebookServiceImpl implements CodebookService {

    @Autowired
    private CodebookRepository codebookRepository;

    @Override
    public Codebook findById(Long id) {
        return codebookRepository.findById(id).orElseThrow(CodebookExistsException::new);
    }

    @Override
    public List<Codebook> findAll() {
        return codebookRepository.findAll();
    }

    @Override
    public Codebook save(Codebook codebook) {
        return codebookRepository.save(codebook);
    }

    @Override
    public void add(Codebook codebook) {
        this.save(codebook);
    }
}
