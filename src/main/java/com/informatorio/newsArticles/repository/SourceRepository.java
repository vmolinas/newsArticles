package com.informatorio.newsArticles.repository;

import com.informatorio.newsArticles.domain.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

    List<Source> findBySourceNameContaining(String sourcename);

}