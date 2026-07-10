package com.workouttracker.domain.repositories.auth;

import com.workouttracker.domain.entities.auth.Role;

import java.util.Optional;

public interface RoleRepository {
    Optional<Role> findByRole(String role);
}
