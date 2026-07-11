package com.workouttracker.domain.repositories.auth;

import com.workouttracker.domain.entities.auth.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
