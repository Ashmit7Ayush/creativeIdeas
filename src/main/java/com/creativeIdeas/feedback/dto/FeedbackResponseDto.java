package com.creativeIdeas.feedback.dto;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class FeedbackResponseDto {
    private String id;
    private String ideaId;
    private String content;
    private String author;
    private Instant createdAt;
    private List<FeedbackResponseDto> replies;
}
