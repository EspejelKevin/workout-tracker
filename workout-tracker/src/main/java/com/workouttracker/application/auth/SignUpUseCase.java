package com.workouttracker.application.auth;

import com.workouttracker.domain.dto.auth.request.SignUpRequest;
import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.entities.auth.Role;
import com.workouttracker.domain.entities.auth.User;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.auth.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SignUpUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpUseCase(UserRepository userRepository, RoleRepository roleRepository,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Response execute(SignUpRequest request) {
        String transactionId = UUID.randomUUID().toString();

        this.userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new RuntimeException("User already exists with email: " + request.getEmail());
                });

        this.userRepository.findByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new RuntimeException("User already exists with username: " + request.getUsername());
                });

        Role role = this.roleRepository.findByRole("REGULAR_USER")
                .orElseThrow(() -> new RuntimeException("Error while saving user"));

        User user = new User();
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(this.passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        this.userRepository.save(user);

        Map<String, String> data = new HashMap<>();
        data.put("message", "SignUp exitoso");

        return new Response(data, transactionId);
    }
}
