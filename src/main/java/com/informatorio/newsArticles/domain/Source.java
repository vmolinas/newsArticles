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
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String sourceName;
    private String code;
    @FutureOrPresent
    private LocalDate createdAt;
    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private Set<Article> articles = new HashSet<>();

    public Source() {
    }

    public Source(Long id, String sourceName, String code, LocalDate createdAt) {
        this.id = id;
        this.sourceName = sourceName;
        this.code = code;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String name) {
        this.sourceName = name;
        this.code = name.toLowerCase().replace(' ','-');
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code =  code;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Source source = (Source) o;
        return Objects.equals(id, source.id) && Objects.equals(sourceName, source.sourceName) && Objects.equals(code, source.code) && Objects.equals(createdAt, source.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceName, code, createdAt);
    }

    @Override
    public String toString() {
        return "Source{" +
                "id=" + id +
                ", sourceName='" + sourceName + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}