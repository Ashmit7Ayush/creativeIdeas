package com.creativeIdeas.service;

import com.creativeIdeas.elastic.IdeaDocument;
import com.creativeIdeas.elasticrepository.IdeaSearchRepository;
import com.creativeIdeas.entity.Idea;
import com.creativeIdeas.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IdeaIndexService {
    private final IdeaSearchRepository ideaSearchRepository;

    public void indexIdea(Idea idea){
        IdeaDocument doc = IdeaDocument.builder()
                .id(String.valueOf(idea.getId()))
                .title(idea.getTitle())
                .content(getLatestVersionContent(idea))
                .tags(idea.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
                .domain(idea.getDomain().getName())
                .popularityScore(idea.getPopularityScore())
                .createdAt(Instant.from(idea.getCreatedAt()))
                .status(idea.getStatus().toString())
                .build();

        ideaSearchRepository.save(doc);
    }

    private String getLatestVersionContent(Idea idea) {
        //todo : extract
        return idea.getTitle() + "-test";
    }
}
