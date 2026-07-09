package com.workouttracker.domain.repositories.workout;

import com.workouttracker.domain.entities.workout.PlanSession;

import java.util.List;
import java.util.Optional;

public interface PlanSessionRepository {
    PlanSession save(PlanSession planSession);
    List<PlanSession> findAllByUserId();
    Optional<PlanSession> findByIdAndUserId(Long id, Long userId);
    Optional<PlanSession> findByPlanIdAndUserId(Long planId, Long userId);
    PlanSession update(PlanSession planSession);
    PlanSession updateStatus(Long id, String status);
    void deleteByIdAndUserId(Long id, Long userId);
}
