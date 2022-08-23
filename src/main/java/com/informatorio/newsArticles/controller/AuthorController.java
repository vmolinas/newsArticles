package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.converter.AuthorConverter;
import com.informatorio.newsArticles.domain.Author;
import com.informatorio.newsArticles.dto.AuthorDTO;
import com.informatorio.newsArticles.dto.AuthorPageDTO;
import com.informatorio.newsArticles.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final AuthorConverter authorConverter;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, AuthorConverter authorConverter) {
        this.authorRepository = authorRepository;
        this.authorConverter = authorConverter;
    }

    /* ALTA */
    @PostMapping("/author")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = authorConverter.toEntity(authorDTO);
        author = authorRepository.save(author);
        return new ResponseEntity<>(authorConverter.toDto(author), HttpStatus.CREATED);
    }

    /* BAJA */
    @PostMapping("/author/{id}/delete")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* MODIFICACION */
    @PutMapping("/author/{id}/modify")
    public ResponseEntity<?> modifyById(@PathVariable Long id, @RequestBody @Valid AuthorDTO authorDTO) {
        Optional<Author> wantedAuthor = authorRepository.findById(id);
        if (wantedAuthor.isPresent()) {
            Author authorToModify = wantedAuthor.get();
            Author author = authorConverter.toEntity(authorDTO);
            authorToModify.setFirstName(author.getFirstName());
            authorToModify.setLastName(author.getLastName());
            authorToModify.setFullName(author.getFullName());
            author = authorRepository.save(authorToModify);
            return new ResponseEntity<>(authorConverter.toDto(author), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* CONSULTAR_POR_ID */
    @GetMapping("/author/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return new ResponseEntity<>(authorConverter.toDto(author.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* LISTAR_TODO */
    @GetMapping("/author")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                     @RequestParam(defaultValue = "5") @Positive int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> authorPage = authorRepository.findAll(pageable);
        AuthorPageDTO authorPageDTO = new AuthorPageDTO(authorPage.getNumber(),
                authorPage.getSize(),
                authorPage.getTotalElements(),
                authorPage.getTotalPages());
        return new ResponseEntity<>(authorPage, HttpStatus.OK);
    }
}