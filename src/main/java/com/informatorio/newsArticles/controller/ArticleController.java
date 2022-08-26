package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<Article> getAll() {
        List<Article> articles = articleRepository.findAll();
        return articles;
    }

    /*--CONSULTA(OBTENER TODOS LOS ARTICULOS BUSCADOS EN EL TITULO Y DESCRIPCION)--*/
    @GetMapping("/article/{title}/{description}")
    public List<Article> searchByWord(@PathVariable @Valid @Size(min=4) String title,@PathVariable @Valid @Size(min=4)  String description) {
        return articleRepository.findByTitleAndDescriptionContaining(title, description);
    }
}