package com.creativeIdeas.ideas.entity;

import com.creativeIdeas.tags.entity.Tag;
import lombok.*;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * status uses enum (DRAFT, PUBLISHED, etc)
 * tags is a many-to-many relationship.
 */
@Entity
@Table(name = "ideas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "domain_id")
    private Domain domain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdeaStatus status;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant publishedAt;

    private Double popularityScore;

    @ManyToMany
    @JoinTable(
            name = "idea_tags",
            joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags;
}
