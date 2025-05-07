package com.creativeIdeas.feedback.service;

import com.creativeIdeas.feedback.dto.FeedbackRequestDto;
import com.creativeIdeas.feedback.dto.FeedbackResponseDto;

import java.util.List;

public interface FeedbackService {
    FeedbackResponseDto addFeedback(String ideaId, String username, FeedbackRequestDto feedbackRequestDto);
    List<FeedbackResponseDto> getFeedbackForIdea(String ideaId);
    void likeIdea(String ideaId, String username);
    long getLikeCount(String ideaId);
}
