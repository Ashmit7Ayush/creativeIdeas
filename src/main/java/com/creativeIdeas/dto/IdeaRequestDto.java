package com.creativeIdeas.dto;

import lombok.Data;

import java.util.List;

@Data
public class IdeaRequestDto {
    private String title;
    private String domain;
    private List<String> tags;
}
