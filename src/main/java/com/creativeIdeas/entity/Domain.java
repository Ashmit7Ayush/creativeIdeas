package com.creativeIdeas.entity;

import lombok.*;
import jakarta.persistence.*;


/**
 * Represents categories like tech, education, etc.
 * One idea belongs to one domain.
 */

@Entity
@Table(name = "domains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}
