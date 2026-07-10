package com.workouttracker.infrastructure.repositories.workout.planexercise;

import com.workouttracker.domain.entities.workout.PlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPlanExerciseMySQLRepository extends JpaRepository<PlanExercise, Long> {
    List<PlanExercise> findAllByPlanIdAndPlanUserId(Long planId, Long userId);
    void deleteByPlanIdAndExerciseId(Long planId, Long exerciseId);
}
