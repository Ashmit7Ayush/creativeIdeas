package com.creativeIdeas.entity;
import jakarta.persistence.*;
import lombok.*;


/**
 * Stores role info like ADMIN, USER.
 * @Entity maps to DB.
 * @Builder, @Data (from Lombok) auto-generates boilerplate.
 */

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}

