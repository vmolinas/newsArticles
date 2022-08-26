package com.informatorio.newsArticles.repository;

import com.informatorio.newsArticles.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitleAndDescriptionContaining(String title, String description);

}