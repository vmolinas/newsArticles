package com.informatorio.newsArticles.domain;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    private String fullName;
    @FutureOrPresent
    private LocalDate createdAt;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, String fullName, LocalDate createdAt, Set<Article> articles
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.createdAt = createdAt;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Article> getArticles() {
        return articles;
    }
    public void addArticle(Article article) {
        articles.add(article);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(fullName, author.fullName) && Objects.equals(createdAt, author.createdAt) && Objects.equals(articles, author.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, fullName, createdAt, articles);
    }

    @Override
    public String toString() {
        return "Author{" +
                ", id='" + id + '\'' +
                ", name='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", createdAt=" + createdAt +
                ", articles=" + articles +
                '}';
    }
}