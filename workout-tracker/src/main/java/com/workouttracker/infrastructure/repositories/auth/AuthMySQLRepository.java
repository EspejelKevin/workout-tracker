package com.workouttracker.infrastructure.repositories.auth;

import com.workouttracker.domain.entities.auth.User;
import com.workouttracker.domain.repositories.auth.AuthRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthMySQLRepository implements AuthRepository {
    private final MySQLRepository mySQLRepository;

    public AuthMySQLRepository(MySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public User save(User user) {
        return this.mySQLRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.mySQLRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.mySQLRepository.findByUsername(username);
    }
}
