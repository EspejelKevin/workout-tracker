package com.workouttracker.domain.dto.workout.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanSessionRequest {
    @JsonProperty("schedule_time")
    @NotNull(message = "schedule_time is required")
    @FutureOrPresent(message = "schedule_time must be scheduled for a future or present datetime")
    private LocalDateTime scheduleTime;

    @JsonProperty("plan_id")
    @NotNull(message = "plan_id is required")
    @Min(value = 1, message = "plan_id must be greater than 0")
    private Long planID;
}
