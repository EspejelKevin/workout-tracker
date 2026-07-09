package com.workouttracker.infrastructure.repositories.workout.exercise;

import com.workouttracker.domain.entities.workout.Exercise;
import com.workouttracker.domain.repositories.workout.ExerciseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExerciseMySQLRepository implements ExerciseRepository {
    private final MySQLRepository mySQLRepository;

    public ExerciseMySQLRepository(MySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public List<Exercise> findAllExerciseWithCategories() {
        return this.mySQLRepository.findAllExerciseWithCategories();
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return this.mySQLRepository.findById(id);
    }
}
