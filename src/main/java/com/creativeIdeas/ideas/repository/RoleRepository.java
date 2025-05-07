package com.creativeIdeas.ideas.repository;

import com.creativeIdeas.ideas.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * fetch roles by name (like ADMIN or USER
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
