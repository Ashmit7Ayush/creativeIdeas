package com.creativeIdeas.search.controller;

import com.creativeIdeas.search.dto.SearchRequestDto;
import com.creativeIdeas.search.dto.SearchResponseDto;
import com.creativeIdeas.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping
    public ResponseEntity<List<SearchResponseDto>> searchIdeas(@RequestBody SearchRequestDto searchRequestDto){
        try{
            List<SearchResponseDto> searchResponseDtoList = searchService.searchIdeas(searchRequestDto);
            return ResponseEntity.ok(searchResponseDtoList);
        }catch (IOException e){
            return ResponseEntity.internalServerError().build();
        }
    }
}

/**
 * POST /api/search
 * Content-Type: application/json

  {
    "query": "machine learning",
    "tags": ["AI", "Education"],
    "domain": "education",
    "authorUsername": "john_doe",
    "status": "PUBLISHED",
    "page": 0,
    "size": 5,
    "sortBy": "popularityScore",
    "sortOrder": "desc"
  }

 [
 {
 "id": 42,
 "title": "Machine Learning for Kids",
 "content": "Teaching ML basics to school students...",
 "authorUsername": "john_doe",
 "tags": ["AI", "Education"],
 "domain": "education",
 "popularityScore": 32.1,
 "createdAt": "2025-05-01T12:00:00Z",
 "status": "PUBLISHED"
 }
 ]


 */
