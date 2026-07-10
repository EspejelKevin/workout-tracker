package com.workouttracker.infrastructure.repositories.auth.role;

import com.workouttracker.domain.entities.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleMySQLRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
