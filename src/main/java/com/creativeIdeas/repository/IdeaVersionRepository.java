package com.creativeIdeas.repository;

import com.creativeIdeas.entity.Idea;
import com.creativeIdeas.entity.IdeaVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * track and retrieve version history of ideas
 */
public interface IdeaVersionRepository extends JpaRepository<IdeaVersion, Long> {
    List<IdeaVersion> findByIdea(Idea idea);
}
