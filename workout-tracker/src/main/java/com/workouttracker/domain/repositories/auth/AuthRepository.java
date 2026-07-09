package com.workouttracker.domain.repositories.auth;

import com.workouttracker.domain.entities.auth.User;

import java.util.Optional;

public interface AuthRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
