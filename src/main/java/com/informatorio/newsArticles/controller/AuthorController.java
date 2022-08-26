package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Author;
import com.informatorio.newsArticles.repository.ArticleRepository;
import com.informatorio.newsArticles.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public List<Author> getAll() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }

    /*--CONSULTA(OBTENER TODOS LOS AUTORES QUE CONTENGAN UN STRING BUSCADO EN EL FULLNAME)--*/
    @GetMapping("/author/{fullname}")
    public List<Author> buscarPorPalabra(@PathVariable String fullname) {
        return authorRepository.findByFullNameContaining(fullname);
    }

    /*--CONSULTA(OBTENER TODOS LOS AUTORES QUE FUERON CREADOS LUEGO DE UNA FECHA DADA)--*/
}