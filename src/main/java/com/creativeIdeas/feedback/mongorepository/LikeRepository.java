package com.creativeIdeas.feedback.mongorepository;

import com.creativeIdeas.feedback.document.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LikeRepository extends MongoRepository<Like, String> {
    long countByIdeaId(String ideaId);
    Optional<Like> findByIdeaIdAndUsername(String ideaId, String username);
}
