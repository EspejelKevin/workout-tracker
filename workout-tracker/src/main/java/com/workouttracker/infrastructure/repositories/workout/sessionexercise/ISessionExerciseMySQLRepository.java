package com.workouttracker.infrastructure.repositories.workout.sessionexercise;

import com.workouttracker.domain.entities.workout.SessionExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISessionExerciseMySQLRepository extends JpaRepository<SessionExercise, Long> {
    List<SessionExercise> findAllByUserId(Long userId);
    Optional<SessionExercise> findByIdAndUserId(Long id, Long userId);
    Optional<SessionExercise> findByExerciseIdAndUserId(Long exerciseId, Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
