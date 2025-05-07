package com.creativeIdeas.indexing.mapper;

import com.creativeIdeas.ideas.entity.Idea;
import com.creativeIdeas.ideas.entity.Tag;
import com.creativeIdeas.indexing.dto.ElasticIdeaDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ElasticIdeaMapper {
    public ElasticIdeaDto toDto(Idea idea){
        return ElasticIdeaDto.builder()
                .id(String.valueOf(idea.getId()))
                .title(idea.getTitle())
                .tags(idea.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
                .authorUsername(idea.getAuthor().getUserName())
                .status(idea.getStatus().name())
                .content("")
                .domain(idea.getDomain().getName())
                .popularityScore(idea.getPopularityScore())
                .createdAt(idea.getCreatedAt())
                .build();
    }
}
