package com.workouttracker.infrastructure.repositories.auth.role;

import com.workouttracker.domain.entities.auth.Role;
import com.workouttracker.domain.repositories.auth.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleMySQLRepository implements RoleRepository {
    private final IRoleMySQLRepository mySQLRepository;

    public RoleMySQLRepository(IRoleMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public Optional<Role> findByRole(String role) {
        return this.mySQLRepository.findByRole(role);
    }
}
