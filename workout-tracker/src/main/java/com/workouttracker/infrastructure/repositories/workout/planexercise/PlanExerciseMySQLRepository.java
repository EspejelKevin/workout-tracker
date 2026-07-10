package com.workouttracker.infrastructure.repositories.workout.planexercise;

import com.workouttracker.domain.entities.workout.PlanExercise;
import com.workouttracker.domain.repositories.workout.PlanExerciseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlanExerciseMySQLRepository implements PlanExerciseRepository {
    private final IPlanExerciseMySQLRepository mySQLRepository;

    public PlanExerciseMySQLRepository(IPlanExerciseMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public PlanExercise save(PlanExercise planExercise) {
        return this.mySQLRepository.save(planExercise);
    }

    @Override
    public List<PlanExercise> findAllByPlanIdAndPlanUserId(Long planId, Long userId) {
        return this.mySQLRepository.findAllByPlanIdAndPlanUserId(planId, userId);
    }

    @Override
    public PlanExercise update(PlanExercise planExercise) {
        return this.mySQLRepository.save(planExercise);
    }

    @Override
    public void deleteByPlanIdAndExerciseId(Long planId, Long exerciseId) {
        this.mySQLRepository.deleteByPlanIdAndExerciseId(planId, exerciseId);
    }
}
