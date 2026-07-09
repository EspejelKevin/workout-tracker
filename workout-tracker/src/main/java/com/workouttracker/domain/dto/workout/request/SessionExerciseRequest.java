package com.workouttracker.domain.dto.workout.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionExerciseRequest {
    @JsonProperty("exercise_id")
    @NotNull(message = "exercise_id is required")
    @Min(value = 1, message = "exercise_id must be greater than 0")
    private Long exerciseId;
}
