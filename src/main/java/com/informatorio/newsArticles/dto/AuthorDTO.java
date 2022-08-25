package com.informatorio.newsArticles.dto;

import com.informatorio.newsArticles.domain.Article;
import com.informatorio.newsArticles.domain.Author;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class AuthorDTO extends Author {

    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String fullName;
    @NotBlank @FutureOrPresent
    private LocalDate createAt;
    @NotBlank
    private Set<Article> articles = new HashSet<>();

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String lastName, String fullName, LocalDate createAt, Set<Article> articles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.createAt = createAt;
        this.articles = articles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.fullName = firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        AuthorDTO authorDTO = (AuthorDTO) o;
        return Objects.equals(id, authorDTO.id) && Objects.equals(firstName, authorDTO.firstName) && Objects.equals(lastName, authorDTO.lastName) && Objects.equals(fullName, authorDTO.fullName) && Objects.equals(createAt, authorDTO.createAt) && Objects.equals(articles, authorDTO.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fullName, createAt, articles);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createAt=" + createAt +
                ", articles=" + articles +
                '}';
    }
}