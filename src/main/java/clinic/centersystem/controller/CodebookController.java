package clinic.centersystem.controller;

import clinic.centersystem.model.Codebook;
import clinic.centersystem.service.CodebookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/med-diag", produces = MediaType.APPLICATION_JSON_VALUE)
public class CodebookController {

    private final CodebookServiceImpl codebookService;

    public CodebookController(CodebookServiceImpl codebookService) {
        this.codebookService = codebookService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Codebook>> getCodebook() {
        List<Codebook> codebooks = this.codebookService.findAll();
        return new ResponseEntity<>(codebooks, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CCADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addCodebook(@RequestBody Codebook codebook) {
        this.codebookService.add(codebook);
        return new ResponseEntity<>("Code successfuly added", HttpStatus.OK);
    }
}
