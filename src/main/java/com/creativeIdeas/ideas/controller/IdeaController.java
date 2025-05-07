package com.creativeIdeas.ideas.controller;

import com.creativeIdeas.ideas.dto.IdeaRequestDto;
import com.creativeIdeas.ideas.dto.IdeaResponseDto;
import com.creativeIdeas.ideas.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/ideas")
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;

    @PostMapping
    public ResponseEntity<IdeaResponseDto> createIdea(@RequestBody IdeaRequestDto dto, Principal principal){
        String userName = principal.getName();
        IdeaResponseDto ideaResponseDto = ideaService.createIdea(dto, userName);
        return ResponseEntity.ok(ideaResponseDto);
    }

    @PutMapping("/{id")
    public ResponseEntity<IdeaResponseDto> updateIdea(@PathVariable UUID id, @RequestBody IdeaRequestDto dto, Principal principal) throws AccessDeniedException {
        String userName = principal.getName();
        IdeaResponseDto ideaResponseDto = ideaService.updateIdea(id, dto, userName);
        return ResponseEntity.ok(ideaResponseDto);
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<IdeaResponseDto> publishIdea(@PathVariable UUID id, Principal principal) throws AccessDeniedException {
        String userName = principal.getName();
        IdeaResponseDto ideaResponseDto = ideaService.publishIdea(id, userName);
        return ResponseEntity.ok(ideaResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<IdeaResponseDto>> getUserIdeas(@RequestParam (defaultValue = "false") boolean drafts, Principal principal){
        String userName = principal.getName();
        List<IdeaResponseDto> ideaResponseDtoList = ideaService.getUserIdeas(userName, drafts);
        return ResponseEntity.ok(ideaResponseDtoList);
    }
}
