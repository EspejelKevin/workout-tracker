package com.workouttracker.domain.repositories.workout;

import com.workouttracker.domain.entities.workout.PlanExercise;

import java.util.List;

public interface PlanExerciseRepository {
    PlanExercise save(PlanExercise planExercise);
    List<PlanExercise> findAllByPlanIdAndPlanUserId(Long planId, Long userId);
    PlanExercise update(PlanExercise planExercise);
    void deleteByPlanIdAndExerciseId(Long planId, Long exerciseId);
}
