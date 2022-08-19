package com.informatorio.newsArticles.converter;

import com.informatorio.newsArticles.domain.Source;
import com.informatorio.newsArticles.dto.SourceDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SourceConverter {

    public SourceDTO toDto(Source source) {
        return new SourceDTO(source.getId(),
                source.getName(),
                source.getCode(),
                source.getCreateAt(),
                source.getArticles());
    }

    public Set<SourceDTO> toDto(Set<Source> sources) {
        return sources.stream()
                .map(source -> toDto(source))
                .collect(Collectors.toSet());
    }

    public Source toEntity(SourceDTO sourceDTO) {
        return new Source(sourceDTO.getName(),
                sourceDTO.getCode(),
                sourceDTO.getCreateAt(),
                sourceDTO.getArticles());
    }

    public Set<Source> toEntity(Set<SourceDTO> sourcesDTO) {
        return sourcesDTO.stream()
                .map(sourceDTO -> toEntity(sourceDTO))
                .collect(Collectors.toSet());
    }
}