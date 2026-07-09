package com.workouttracker.domain.dto.workout.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExerciseRequest {
    @NotBlank(message = "name is required")
    @Size(max = 30, message = "max len is 30 for name")
    private String name;

    @NotBlank(message = "description is required")
    @Size(max = 255, message = "max len is 255 for description")
    private String description;

    @JsonProperty("category_id")
    @NotNull(message = "category_id is required")
    @Min(value = 1, message = "category_id must be greater than 0")
    private Long categoryId;
}
