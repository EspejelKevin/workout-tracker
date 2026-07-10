package com.workouttracker.application.auth;

import com.workouttracker.domain.dto.auth.request.LogInRequest;
import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.entities.auth.User;
import com.workouttracker.domain.repositories.auth.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LogInUseCase {
    private final UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Response execute(LogInRequest request) {
        String transactionId = UUID.randomUUID().toString();

        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getEmail()));

        if (!this.isValidPassword(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password for user: " + request.getEmail());
        }

        String token = this.generateToken("secretkey", "REGULAR_USER", user.getId());

        Map<String, String> data = new HashMap<>();
        data.put("message", "LogIn exitoso");
        data.put("token", token);

        return new Response(data, transactionId);
    }

    private boolean isValidPassword(String password, String hashedPassword) {
        return String.format("password%s", password).equals(hashedPassword);
    }

    private String generateToken(String key, String role, Long userId) {
        return String.format("mock token %s%s%d", key, role, userId);
    }
}
