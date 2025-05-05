package com.creativeIdeas.mongorepository;

import com.creativeIdeas.document.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Get all feedback for an idea.
 * Get all likes vs. comments separately.
 * Get feedback by user.
 */
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByIdeaId(Long ideaId);
    List<Feedback> findByUserId(Long userId);
    List<Feedback> findByIdeaIdAndType(String ideaId, String type);
}
