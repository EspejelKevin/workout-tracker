package com.workouttracker.infrastructure.repositories.workout.plan;

import com.workouttracker.domain.entities.workout.Plan;
import com.workouttracker.domain.repositories.workout.PlanRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlanMySQLRepository implements PlanRepository {
    private final IPlanMySQLRepository mySQLRepository;

    public PlanMySQLRepository(IPlanMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public Plan save(Plan plan) {
        return this.mySQLRepository.save(plan);
    }

    @Override
    public List<Plan> findAllByUserId(Long userId) {
        return this.mySQLRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<Plan> findByIdAndUserId(Long id, Long userId) {
        return this.mySQLRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Plan update(Plan plan) {
        return this.mySQLRepository.save(plan);
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        this.mySQLRepository.deleteByIdAndUserId(id, userId);
    }
}
