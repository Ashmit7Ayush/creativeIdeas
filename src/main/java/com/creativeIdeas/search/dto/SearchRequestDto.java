package com.creativeIdeas.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class SearchRequestDto {
    private String query; //the keyword to search (fuzzy + full-text on title/content)
    private String domain;
    private List<String> tags;
    private String authorUsername;
    private String status;
    private int page = 0;
    private int size = 10;
    private String sortBy = "popularityScore";
    private String sortOrder = "desc";
}
