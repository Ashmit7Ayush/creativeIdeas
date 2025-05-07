package com.creativeIdeas.ideas.repository;

import com.creativeIdeas.ideas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * user login/registration validation via username or email.
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
    Optional<User> findByEmail(String email);
}
