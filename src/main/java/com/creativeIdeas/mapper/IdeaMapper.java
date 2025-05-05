package com.creativeIdeas.mapper;

import com.creativeIdeas.dto.IdeaResponseDto;
import com.creativeIdeas.entity.Idea;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * mapper to convert between Idea entity ↔ DTOs
 */
@Component
public class IdeaMapper {
    public IdeaResponseDto toDto(Idea idea){
        IdeaResponseDto ideaResponseDto = new IdeaResponseDto();
        ideaResponseDto.setId(idea.getId());
        ideaResponseDto.setTitle(idea.getTitle());
        ideaResponseDto.setTags(idea.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()));
        ideaResponseDto.setAuthor(idea.getAuthor().getUserName());
        ideaResponseDto.setPopularityScore(idea.getPopularityScore());
        ideaResponseDto.setDomain(idea.getDomain().getName());
        ideaResponseDto.setStatus(idea.getStatus().name());
        ideaResponseDto.setCreatedAt(idea.getCreatedAt());
        return ideaResponseDto;
    }
}
