package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.domain.Author;
import com.informatorio.newsArticles.dto.PageDTO;
import com.informatorio.newsArticles.repository.ArticleRepository;
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
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /*--ALTA--*/
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        return new ResponseEntity<>(articleRepository.save(article), HttpStatus.CREATED);
    }

    /*--BAJA--*/
    @DeleteMapping("/author/{idArticle}")
    public void deleteArticle(@PathVariable Long idArticle) {
        articleRepository.deleteById(idArticle);
    }

    /*--MODIFICACION--*/
    @PutMapping("/article/{idArticle}")
    public Article modifyArticle(@PathVariable Long idArticle, @RequestBody Article article) {
        Article articles = articleRepository.findById(idArticle).get();
        articles.setTitle(article.getTitle());
        return articleRepository.save(articles);
    }

    /*--CONSULTA(OBTENER TODOS LOS ARTICULOS)--*/
    @GetMapping("/article")
    public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                     @RequestParam(defaultValue = "5") @Positive int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Article> articlePage = articleRepository.findAll(pageable);
        PageDTO articlePageDTO = new PageDTO(articlePage.getNumber(),
                articlePage.getSize(),
                articlePage.getTotalElements(),
                articlePage.getTotalPages());
        return new ResponseEntity<>(articlePage, HttpStatus.OK);
    }

//    @GetMapping("/article")
//    public List<Article> getAll() {
//        List<Article> articles = articleRepository.findAll();
//        return articles;
//    }

    /*--CONSULTA(OBTENER TODOS LOS ARTICULOS BUSCADOS EN EL TITULO Y DESCRIPCION)--*/
    @GetMapping("/article/{title}/{description}")
    public List<Article> searchByWord(@PathVariable @Valid @Size(min=4) String title,@PathVariable @Valid @Size(min=4)  String description) {
        return articleRepository.findByTitleAndDescriptionContaining(title, description);
    }
}