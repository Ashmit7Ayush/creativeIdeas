package com.creativeIdeas.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
public class IdeaResponseDto {
    private UUID id;
    private String title;
    private String domain;
    private List<String> tags;
    private String author;
    private String status;
    private double popularityScore;
    private Instant createdAt;
}
