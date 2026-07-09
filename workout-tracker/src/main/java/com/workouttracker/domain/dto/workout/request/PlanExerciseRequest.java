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
public class PlanExerciseRequest {
    @JsonProperty("exercise_order")
    @NotNull(message = "exercise_order is required")
    @Min(value = 1, message = "exercise_order must be greater than 0")
    private Integer exerciseOrder;

    @JsonProperty("exercise_id")
    @NotNull(message = "exercise_id is required")
    @Min(value = 1, message = "exercise_id must be greater than 0")
    private Long exerciseId;

    @JsonProperty("plan_id")
    @NotNull(message = "plan_id is required")
    @Min(value = 1, message = "plan_id must be greater than 0")
    private Long planId;

    private Integer sets;
    private Integer repetitions;
    private Integer duration;
    private Double weights;
}
