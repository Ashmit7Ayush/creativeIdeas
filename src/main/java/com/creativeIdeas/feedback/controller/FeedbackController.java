package com.creativeIdeas.feedback.controller;

import com.creativeIdeas.feedback.dto.FeedbackRequestDto;
import com.creativeIdeas.feedback.dto.FeedbackResponseDto;
import com.creativeIdeas.feedback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ideas/{ideaId}/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    // add comment
    @PostMapping
    public ResponseEntity<FeedbackResponseDto> addFeedback(
            @PathVariable String ideaId,
            @RequestBody FeedbackRequestDto feedbackRequestDto,
            @RequestHeader("X-User") String username // from auth
    ){
        FeedbackResponseDto feedbackResponseDto = feedbackService.addFeedback(ideaId, username, feedbackRequestDto);
        return ResponseEntity.ok(feedbackResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDto>> getFeedback(
            @PathVariable String ideaId
    ){
        List<FeedbackResponseDto> feedbackResponseDtoList = feedbackService.getFeedbackForIdea(ideaId);
        return ResponseEntity.ok(feedbackResponseDtoList);
    }

    @PostMapping("/like")
    public ResponseEntity<Void> likeIdea(
            @PathVariable String ideaId,
            @RequestHeader("X-User") String username
    ){
        feedbackService.likeIdea(ideaId, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/like/getCount")
    public ResponseEntity<Long> getLikeCount(
            @PathVariable String ideaId
    ){
        long count = feedbackService.getLikeCount(ideaId);
        return ResponseEntity.ok(count);
    }
    /**
     * todo
     * Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
     * String username = authentication.getName();
     */
}
