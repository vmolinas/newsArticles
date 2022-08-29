package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Author;
import com.informatorio.newsArticles.dto.PageDTO;
import com.informatorio.newsArticles.repository.ArticleRepository;
import com.informatorio.newsArticles.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Validated
@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, ArticleRepository articleRepository) {
        this.authorRepository = authorRepository;
        this.articleRepository = articleRepository;
    }

    /*--ALTA--*/
    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorRepository.save(author), HttpStatus.CREATED);
    }

    /*--BAJA--*/
    @DeleteMapping("/author/{idAuthor}")
    public void deleteAuthor(@PathVariable Long idAuthor) {
        authorRepository.deleteById(idAuthor);
    }

    /*--MODIFICACION--*/
    @PutMapping("/author/{idAuthor}")
    public Author modifyAuthor(@PathVariable("idAuthor") Long idAuthor, @RequestBody Author author) {
        Author authors = authorRepository.findById(idAuthor).get();
        authors.setFirstName(author.getFirstName());
        return authorRepository.save(authors);
    }

    /*--CONSULTA(OBTENER TODOS LOS AUTORES)--*/
    @GetMapping("/author")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                     @RequestParam(defaultValue = "5") @Positive int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authorPage = authorRepository.findAll(pageable);
        PageDTO authorPageDTO = new PageDTO(authorPage.getNumber(),
                authorPage.getSize(),
                authorPage.getTotalElements(),
                authorPage.getTotalPages());
        return new ResponseEntity<>(authorPage, HttpStatus.OK);
    }
//    @GetMapping("/author")
//    public List<Author> getAll() {
//        List<Author> authors = authorRepository.findAll();
//        return authors;
//    }

    /*--CONSULTA(OBTENER TODOS LOS AUTORES QUE CONTENGAN UN STRING BUSCADO EN EL FULLNAME)--*/
    @GetMapping("/author/{fullname}")
    public List<Author> buscarPorPalabra(@PathVariable String fullname) {
        return authorRepository.findByFullNameContaining(fullname);
    }

    /*--CONSULTA(OBTENER TODOS LOS AUTORES QUE FUERON CREADOS LUEGO DE UNA FECHA DADA)--*/

}