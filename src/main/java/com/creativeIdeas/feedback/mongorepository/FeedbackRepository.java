package com.creativeIdeas.feedback.mongorepository;

import com.creativeIdeas.feedback.document.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findByIdeaId(String ideaId);
    List<Feedback> findByParentId(String parentId);
}
