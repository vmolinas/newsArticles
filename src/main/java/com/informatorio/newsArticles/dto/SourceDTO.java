package com.informatorio.newsArticles.dto;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.domain.Source;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SourceDTO extends Source {

    private Long id;
    private String name;
    private String code;
    private LocalDate createAt;
    private Set<Article> articles = new HashSet<>();

    public SourceDTO() {
    }

    public SourceDTO(Long id, String name, String code, LocalDate createAt, Set<Article> articles) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createAt = createAt;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceDTO sourceDTO = (SourceDTO) o;
        return Objects.equals(id, sourceDTO.id) && Objects.equals(name, sourceDTO.name) && Objects.equals(code, sourceDTO.code) && Objects.equals(createAt, sourceDTO.createAt) && Objects.equals(articles, sourceDTO.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createAt, articles);
    }

    @Override
    public String toString() {
        return "SourceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createAt=" + createAt +
                ", articles=" + articles +
                '}';
    }
}