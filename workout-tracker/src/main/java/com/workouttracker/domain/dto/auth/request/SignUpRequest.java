package com.workouttracker.domain.dto.auth.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SignUpRequest {
    @NotBlank(message = "name is required")
    @Size(max = 30, message = "max len is 30 for name")
    private String name;

    @NotBlank(message = "lastname is required")
    @Size(max = 30, message = "max len is 30 for lastname")
    private String lastname;

    @NotBlank(message = "email is required")
    @Size(max = 30, message = "max len is 30 for email")
    @Email(message = "invalid format for email")
    private String email;

    @NotBlank(message = "username is required")
    @Size(max = 30, message = "max len is 30 for username")
    private String username;

    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,30}$",
            message = "8-30 characters long, include upper and lower letter, digit, special character without spaces")
    private String password;
}
