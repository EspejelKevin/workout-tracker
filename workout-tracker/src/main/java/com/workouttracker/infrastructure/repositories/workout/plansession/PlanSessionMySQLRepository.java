package com.workouttracker.infrastructure.repositories.workout.plansession;

import com.workouttracker.domain.entities.workout.PlanSession;
import com.workouttracker.domain.repositories.workout.PlanSessionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlanSessionMySQLRepository implements PlanSessionRepository {
    private final IPlanSessionMySQLRepository mySQLRepository;

    public PlanSessionMySQLRepository(IPlanSessionMySQLRepository mySQLRepository) {
        this.mySQLRepository = mySQLRepository;
    }

    @Override
    public PlanSession save(PlanSession planSession) {
        return this.mySQLRepository.save(planSession);
    }

    @Override
    public List<PlanSession> findAllByUserId(Long userId) {
        return this.mySQLRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<PlanSession> findByIdAndUserId(Long id, Long userId) {
        return this.mySQLRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public Optional<PlanSession> findByPlanIdAndUserId(Long planId, Long userId) {
        return this.mySQLRepository.findByPlanIdAndUserId(planId, userId);
    }

    @Override
    public PlanSession update(PlanSession planSession) {
        return this.mySQLRepository.save(planSession);
    }

    @Override
    public void deleteByIdAndUserId(Long id, Long userId) {
        this.mySQLRepository.deleteByIdAndUserId(id, userId);
    }
}
