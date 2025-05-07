package com.creativeIdeas.ideas.entity;

import lombok.*;
import jakarta.persistence.*;

/**
 * Tags are reusable labels ( AI, Blockchain, etc).
 * Used in many-to-many relationship with ideas
 */
@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

}
