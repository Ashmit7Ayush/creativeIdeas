package com.creativeIdeas.repository;

import com.creativeIdeas.entity.Idea;
import com.creativeIdeas.entity.IdeaStatus;
import com.creativeIdeas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * filtering by status, author, and partial title search (for drafts, publishing)
 */

public interface IdeaRepository extends JpaRepository<Idea, UUID> {
    List<Idea> findByTitleContainingIgnoreCase(String keyword);
    List<Idea> findByStatus(IdeaStatus status);
    List<Idea> findByAuthor(User author);
}
