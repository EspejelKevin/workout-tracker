package com.workouttracker.infrastructure.repositories.workout.exercise;

import com.workouttracker.domain.entities.workout.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IExerciseMySQLRepository extends JpaRepository<Exercise, Long> {
    @Query("""
        SELECT e
        FROM Exercise e
        JOIN FETCH e.category
    """)
    List<Exercise> findAllExerciseWithCategories();
}
