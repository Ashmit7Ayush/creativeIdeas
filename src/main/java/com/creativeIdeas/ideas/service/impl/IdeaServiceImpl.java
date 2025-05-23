package com.creativeIdeas.ideas.service.impl;

import com.creativeIdeas.domain.entity.Domain;
import com.creativeIdeas.domain.service.DomainService;
import com.creativeIdeas.ideas.dto.IdeaRequestDto;
import com.creativeIdeas.ideas.dto.IdeaResponseDto;
import com.creativeIdeas.ideas.entity.*;
import com.creativeIdeas.ideas.mapper.IdeaMapper;
import com.creativeIdeas.domain.repository.DomainRepository;
import com.creativeIdeas.ideas.repository.IdeaRepository;
import com.creativeIdeas.ideas.repository.UserRepository;
import com.creativeIdeas.indexing.service.IdeaIndexService;
import com.creativeIdeas.ideas.service.IdeaService;
import com.creativeIdeas.tags.entity.Tag;
import com.creativeIdeas.tags.repository.TagRepository;
import com.creativeIdeas.tags.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;
    private final DomainRepository domainRepository;
    private final TagRepository tagRepository;
    private final IdeaMapper ideaMapper;
    private final IdeaIndexService ideaIndexService;
    private final TagService tagService;
    private final DomainService domainService;

    /**
     * Validates user + domain + tags, saves in DRAFT
     * @param ideaDto
     * @param userName
     * @return IdeaResponseDto
     */
    @Override
    @Transactional
    public IdeaResponseDto createIdea(IdeaRequestDto ideaDto, String userName) {
        // validating tags and domain
        tagService.validateTags(ideaDto.getTags());
        domainService.validateDomain(ideaDto.getDomain());

        User user = userRepository.findByUsername(userName).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));

        Domain domain = domainRepository.findByName(ideaDto.getDomain()).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Domain Value"));

        Set<Tag> tags = ideaDto.getTags().stream().map(tagName ->
                tagRepository.findByName(tagName).orElseGet(() ->
                        tagRepository.save(new Tag(null, tagName)))).collect(Collectors.toSet());

        Idea idea = new Idea();
        idea.setTitle(ideaDto.getTitle());
        idea.setAuthor(user);
        idea.setStatus(IdeaStatus.DRAFT);
        idea.setTags(tags);
        idea.setDomain(domain);
        idea.setCreatedAt(new Date().toInstant());

        Idea saved = ideaRepository.save(idea);

        return ideaMapper.toDto(saved);
    }


    /**
     * Ensures ownership, updates content
     * @param ideaId
     * @param ideaDto
     * @param userName
     * @return IdeaResponseDto
     * @throws AccessDeniedException
     */
    @Override
    @Transactional
    public IdeaResponseDto updateIdea(UUID ideaId, IdeaRequestDto ideaDto, String userName) throws AccessDeniedException {
        Idea idea = validOwnership(ideaId, userName);

        idea.setTitle(ideaDto.getTitle());
        idea.setDomain(domainRepository.findByName(ideaDto.getDomain()).orElseThrow(() ->
                new ResourceNotFoundException("Invalid input")));

        idea.setTags(ideaDto.getTags().stream().map(tagName -> tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(null, tagName)))).collect(Collectors.toSet()));

        idea.setUpdatedAt(new Date().toInstant());

        return ideaMapper.toDto(ideaRepository.save(idea));
    }


    /**
     * Publishes and indexes to Elasticsearch
     * @param ideaId
     * @param userName
     * @return IdeaResponseDto
     * @throws AccessDeniedException
     */
    @Override
    @Transactional
    public IdeaResponseDto publishIdea(UUID ideaId, String userName) throws AccessDeniedException {
        Idea idea = validOwnership(ideaId, userName);
        idea.setStatus(IdeaStatus.PUBLISHED);
        idea.setPopularityScore(0.0);
        idea.setPublishedAt(new Date().toInstant());

        Idea saved = ideaRepository.save(idea);
        ideaIndexService.indexIdea(idea);

        return ideaMapper.toDto(saved);
    }

    /**
     * Lists user’s ideas, with optional draft filter
     * @param userName
     * @param includeDrafts
     * @return List<IdeaResponseDto>
     */
    @Override
    public List<IdeaResponseDto> getUserIdeas(String userName, boolean includeDrafts) {
        return ideaRepository.findByAuthor(userRepository.findByUsername(userName).get())
                .stream().filter(i -> includeDrafts || i.getStatus() == IdeaStatus.PUBLISHED)
                .map(ideaMapper::toDto)
                .collect(Collectors.toList());
//        return List.of();
    }

    /**
     * Reusable permission check
     * @param id
     * @param username
     * @return Idea
     * @throws AccessDeniedException
     */
    private Idea validOwnership(UUID id, String username) throws AccessDeniedException {
        return ideaRepository.findById(id)
                .filter(i -> i.getAuthor().getUsername().equals(username))
                .orElseThrow(() -> new AccessDeniedException("Not Valid User"));
    }
}
