package com.informatorio.newsArticles.dto;

import com.informatorio.newsArticles.domain.Source;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

public class SourceDTO extends Source {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private LocalDate createdAt;

    public SourceDTO() {
    }

    public SourceDTO(String name, String code, LocalDate createdAt) {
        this.name = name;
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
        return name;
    }

    public void setSourceName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        SourceDTO sourceDTO = (SourceDTO) o;
        return Objects.equals(id, sourceDTO.id) && Objects.equals(name, sourceDTO.name) && Objects.equals(code, sourceDTO.code) && Objects.equals(createdAt, sourceDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code, createdAt);
    }

    @Override
    public String toString() {
        return "SourceDTO{" +
                "id=" + id +
                ", sourceName='" + name + '\'' +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}