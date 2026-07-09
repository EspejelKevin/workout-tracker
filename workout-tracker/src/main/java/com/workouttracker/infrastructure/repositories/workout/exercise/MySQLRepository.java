package com.workouttracker.infrastructure.repositories.workout.exercise;

import com.workouttracker.domain.entities.workout.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MySQLRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllExerciseWithCategories();
}
