package com.workouttracker.domain.dto.workout.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanSessionStatusRequest {
    @NotBlank(message = "status is required")
    @Pattern(regexp = "^(DOING|COMPLETED)$",
            message = "valid options for status are: DOING|COMPLETED")
    private String status;
}
