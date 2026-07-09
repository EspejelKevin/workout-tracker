package com.workouttracker.domain.repositories.workout;

import com.workouttracker.domain.entities.workout.Exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository {
    List<Exercise> findAllExerciseWithCategories();
    Optional<Exercise> findById(Long id);
}
