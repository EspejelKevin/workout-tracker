package com.workouttracker.infrastructure.repositories.workout.sessionexercise;

import com.workouttracker.domain.entities.workout.SessionExercise;
import com.workouttracker.domain.repositories.workout.SessionExerciseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SessionExerciseMySQLRepository implements SessionExerciseRepository {
    private final ISessionExerciseMySQLRepository mySQLRepository;

    public SessionExerciseMySQLRepository(ISessionExerciseMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public SessionExercise save(SessionExercise sessionExercise) {
        return this.mySQLRepository.save(sessionExercise);
    }

    @Override
    public List<SessionExercise> findAllByUserId(Long userId) {
        return this.mySQLRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<SessionExercise> findByIdAndUserId(Long id, Long userId) {
        return this.mySQLRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Optional<SessionExercise> findByExerciseIdAndUserId(Long exerciseId, Long userId) {
        return this.mySQLRepository.findByExerciseIdAndUserId(exerciseId, userId);
    }

    @Override
    public SessionExercise update(SessionExercise sessionExercise) {
        return this.mySQLRepository.save(sessionExercise);
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        this.mySQLRepository.deleteByIdAndUserId(id, userId);
    }
}
