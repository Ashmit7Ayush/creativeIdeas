package com.creativeIdeas.mongorepository;

import com.creativeIdeas.document.Draft;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Get all drafts created by a user.
 * Fetch draft associated with an existing idea.
 */
public interface DraftRepository extends MongoRepository<Draft, String> {
    List<Draft> findByAuthorId(Long id);
    Optional<Draft> findByIdeaId(Long ideaId);
}
