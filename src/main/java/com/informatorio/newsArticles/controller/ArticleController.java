package com.informatorio.newsArticles.controller;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.domain.Source;
import com.informatorio.newsArticles.dto.ArticleDTO;
import com.informatorio.newsArticles.repository.ArticleRepository;
import com.informatorio.newsArticles.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final SourceRepository sourceRepository;

    @Autowired
    public ArticleController (ArticleRepository articleRepository, SourceRepository sourceRepository) {
        this.articleRepository = articleRepository;
        this.sourceRepository = sourceRepository;
    }

    @PostMapping("/article")
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

//    @PostMapping("/article/{idArticle}/source")
//    public ArticleDTO addSourceToArticle(@PathVariable Long idArticle, @RequestBody List<Long> sourcesIds) {
//        Article article = articleRepository.findById(idArticle).orElse(null);
//        List<Source> sources = sourcesIds.stream()
//                .map(sourceRepository::findById)
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//        for (Source source : sources) {
//            article.addSource(source);
//        }
//        articleRepository.save(article);
//        return "OK";
//    }

}