package com.workouttracker.infrastructure.routes.auth;

import com.workouttracker.application.auth.LogInUseCase;
import com.workouttracker.application.auth.SignUpUseCase;
import com.workouttracker.domain.dto.auth.request.LogInRequest;
import com.workouttracker.domain.dto.auth.request.SignUpRequest;
import com.workouttracker.domain.dto.response.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security-management/api/v1")
public class AuthController {
    private final LogInUseCase logInUseCase;
    private final SignUpUseCase signUpUseCase;

    public AuthController(LogInUseCase logInUseCase, SignUpUseCase signUpUseCase) {
        this.logInUseCase = logInUseCase;
        this.signUpUseCase = signUpUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LogInRequest request) {
        Response response = this.logInUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> signup(@Valid @RequestBody SignUpRequest request) {
        Response response = this.signUpUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
