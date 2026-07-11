package com.workouttracker.infrastructure.repositories.auth.user;

import com.workouttracker.domain.entities.auth.User;
import com.workouttracker.domain.repositories.auth.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMySQLRepository implements UserRepository {
    private final IUserMySQLRepository mySQLRepository;

    public UserMySQLRepository(IUserMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public User save(User user) {
        return this.mySQLRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return this.mySQLRepository.findById(userId);
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
