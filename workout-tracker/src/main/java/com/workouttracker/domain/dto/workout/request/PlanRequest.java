package com.workouttracker.domain.dto.workout.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanRequest {
    @NotBlank(message = "name is required")
    @Size(max = 30, message = "max len is 30 for name")
    private String name;

    @NotBlank(message = "description is required")
    @Size(max = 255, message = "max len is 255 for description")
    private String description;

    private String comments = "";
}
