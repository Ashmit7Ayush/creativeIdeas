package com.creativeIdeas.search.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class SearchResponseDto {
    private String id;
    private String title;
    private String content;
    private String authorUsername;
    private List<String> tags;
    private String domain;
    private Double popularityScore;
    private Instant createdAt;
    private String status;
}
