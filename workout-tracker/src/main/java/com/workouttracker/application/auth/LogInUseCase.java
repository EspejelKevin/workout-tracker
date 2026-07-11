package com.workouttracker.application.auth;

import com.workouttracker.domain.dto.auth.request.LogInRequest;
import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.entities.auth.User;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.shared.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LogInUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public LogInUseCase(AuthenticationManager authenticationManager,
                        UserRepository userRepository, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public Response execute(LogInRequest request) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = this.userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found: " + request.getEmail()));

        String token = this.jwtUtils.generateToken(user);

        Map<String, String> data = new HashMap<>();
        data.put("message", "LogIn exitoso");
        data.put("token", token);

        return new Response(data, transactionId);
    }
}
