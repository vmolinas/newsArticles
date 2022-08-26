package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Source;
import com.informatorio.newsArticles.repository.SourceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class SourceController {

    private final SourceRepository sourceRepository;

    public SourceController(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    /*--ALTA--*/
    @PostMapping("/source")
    public ResponseEntity<?> createSource(@RequestBody Source source) {
        return new ResponseEntity<>(sourceRepository.save(source), HttpStatus.CREATED);
    }

    /*--BAJA--*/
    @DeleteMapping("/source/{idSource}")
    public void deleteSource(@PathVariable Long idSource) {
        sourceRepository.deleteById(idSource);
    }

    /*--MODIFICACION--*/
    @PutMapping("/source/{idSource}")
    public Source modifySource(@PathVariable Long idSource, @RequestBody Source source) {
        Source sources = sourceRepository.findById(idSource).get();
        sources.setSourceName(source.getSourceName());
        return sourceRepository.save(sources);
    }

    /*--CONSULTA(OBTENER TODOS LOS SOURCES)*/
    @GetMapping("/source")
    public List<Source> getAll() {
        List<Source> sources = sourceRepository.findAll();
        return sources;
    }

    /*--CONSULTA(OBTENER TODOS LOS SOURCES QUE CONTENGAN UNA PALABRA DADA)*/
    @GetMapping("/source/{sourcename}")
    public List<Source> searchByWord(@PathVariable String sourcename) {
        return sourceRepository.findBySourceNameContaining(sourcename);
    }
}