package com.informatorio.newsArticles.repository;

import com.informatorio.newsArticles.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByFullNameContaining(String fullname);
    List<Author> findByCreatedAtAfter(LocalDate fecha);

}