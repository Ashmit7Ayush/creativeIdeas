package com.creativeIdeas.indexing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElasticIdeaDto {
    private String id;
    private String title;
    private String content;
    private String authorUsername;
    private List<String> tags;
    private String domain;
    private Instant createdAt;
    private String status;
    private Double popularityScore;
}
