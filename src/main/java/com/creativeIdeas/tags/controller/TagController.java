package com.creativeIdeas.tags.controller;

import com.creativeIdeas.tags.dto.TagDTO;
import com.creativeIdeas.tags.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags(){
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PostMapping
    public void createTag(@RequestBody TagDTO tagDTO){
        tagService.createTag(tagDTO.getName());
    }
}
