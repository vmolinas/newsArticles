//package com.informatorio.newsArticles.converter;
//
//import com.informatorio.newsArticles.domain.Author;
//import com.informatorio.newsArticles.dto.AuthorDTO;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class AuthorConverter {
//
//    public AuthorDTO toDto(Author author) {
//        return new AuthorDTO(author.getId(),
//                author.getFirstName(),
//                author.getLastName(),
//                author.getFullName(),
//                author.getCreateAt(),
//                author.getArticles());
//    }
//
//    public Set<AuthorDTO> toDto(Set<Author> authors) {
//        return authors.stream()
//                .map(author -> toDto(author))
//                .collect(Collectors.toSet());
//    }
//
//    public Author toEntity(AuthorDTO authorDTO) {
//        return new Author(authorDTO.getFirstName(),
//                authorDTO.getLastName(),
//                authorDTO.getFullName(),
//                authorDTO.getCreateAt(),
//                authorDTO.getArticles());
//    }
//
//    public Set<Author> toEntity(Set<AuthorDTO> authorsDTO) {
//        return authorsDTO.stream()
//                .map(authorDTO -> toEntity(authorDTO))
//                .collect(Collectors.toSet());
//    }
//}