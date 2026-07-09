package com.workouttracker.domain.repositories.workout;

import com.workouttracker.domain.entities.workout.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
    Plan save(Plan plan);
    List<Plan> findAllByUserId(Long userId);
    Optional<Plan> findByIdAndUserId(Long id, Long userId);
    Plan update(Plan plan);
    void deleteByIdAndUserId(Long id, Long userId);
}
