package com.creativeIdeas.ideas.entity;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * One user has one role.
 *
 * Stores credentials, timestamps, and active status.
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private  Role role;

    private LocalDateTime createdAt;

    private boolean isActive;

}
