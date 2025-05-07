package com.creativeIdeas.feedback.dto;

import lombok.Data;

@Data
public class FeedbackRequestDto {
    private String content;
    private String parentId; // null if top level comment
}
