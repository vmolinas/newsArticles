package com.informatorio.newsArticles.converter;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.dto.ArticleDTO;
import com.informatorio.newsArticles.dto.AuthorDTO;
import com.informatorio.newsArticles.dto.SourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArticleConverter {

    private final AuthorConverter authorConverter;
    private final SourceConverter sourceConverter;

    @Autowired
    public ArticleConverter(AuthorConverter authorConverter, SourceConverter sourceConverter) {
        this.authorConverter = authorConverter;
        this.sourceConverter = sourceConverter;
    }

    public ArticleDTO toDto(Article article) {
        return new ArticleDTO(article.getId(),
                article.getTitle(),
                article.getDescription(),
                article.getUrl(),
                article.getUrlToImage(),
                article.getPublishedAt(),
                article.getPublished(),
                article.getContent(),
                authorConverter.toDto(article.getAuthor()),
                sourceConverter.toDto(article.getSource()));
    }

    private Set<ArticleDTO> toDto(Set<Article> articles) {
        return articles.stream()
                .map(article -> toDto(article))
                .collect(Collectors.toSet());
    }

    public Article toEntity(ArticleDTO articleDTO) {
        return new Article(articleDTO.getTitle(),
                articleDTO.getDescription(),
                articleDTO.getUrl(),
                articleDTO.getUrlToImage(),
                articleDTO.getPublishedAt(),
                articleDTO.getPublished(),
                articleDTO.getContent(),
                authorConverter.toEntity((AuthorDTO) articleDTO.getAuthor()),
                sourceConverter.toEntity((SourceDTO) articleDTO.getSource()));
    }

    private Set<Article> toEntity(Set<ArticleDTO> articlesDTO) {
        return articlesDTO.stream()
                .map(articleDTO -> toEntity(articleDTO))
                .collect(Collectors.toSet());
    }


}