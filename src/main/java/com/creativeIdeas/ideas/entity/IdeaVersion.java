package com.creativeIdeas.ideas.entity;

import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;


/**
 * Tracks versions of idea content.
 */
@Entity
@Table(name = "idea_versions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdeaVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idea_id")
    private Idea idea;

    @Column(columnDefinition = "Text")
    private String content;

    private Integer versionNo;

    private LocalDateTime createdAt;
}
