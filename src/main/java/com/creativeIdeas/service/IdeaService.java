package com.creativeIdeas.service;

import com.creativeIdeas.dto.IdeaRequestDto;
import com.creativeIdeas.dto.IdeaResponseDto;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

/**
 * Create/update ideas
 * Publish an idea
 * Get all ideas for a user (filter drafts optionally)
 */
public interface IdeaService {
    IdeaResponseDto createIdea(IdeaRequestDto ideaDto, String userName);
    IdeaResponseDto updateIdea(UUID ideaId, IdeaRequestDto ideaDto, String userName) throws AccessDeniedException;
    IdeaResponseDto publishIdea(UUID ideaId, String userName) throws AccessDeniedException;
    List<IdeaResponseDto> getUserIdeas(String userName, boolean includeDrafts);
}
