package com.workouttracker.domain.repositories.workout;

import com.workouttracker.domain.entities.workout.SessionExercise;

import java.util.List;
import java.util.Optional;

public interface SessionExerciseRepository {
    SessionExercise save(SessionExercise sessionExercise);
    List<SessionExercise> findAllByUserId(Long userId);
    Optional<SessionExercise> findByIdAndUserId(Long id, Long userId);
    Optional<SessionExercise> findByExerciseIdAndUserId(Long exerciseId, Long userId);
    SessionExercise update(SessionExercise sessionExercise);
    SessionExercise updateStatus(Long id, String status);
    void deleteByIdAndUserId(Long id, Long userId);
}
