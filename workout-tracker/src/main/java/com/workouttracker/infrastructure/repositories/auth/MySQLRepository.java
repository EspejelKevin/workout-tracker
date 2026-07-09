package com.workouttracker.infrastructure.repositories.auth;

import com.workouttracker.domain.entities.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MySQLRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
