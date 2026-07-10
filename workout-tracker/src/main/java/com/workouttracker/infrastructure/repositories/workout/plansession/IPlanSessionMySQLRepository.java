package com.workouttracker.infrastructure.repositories.workout.plansession;

import com.workouttracker.domain.entities.workout.PlanSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPlanSessionMySQLRepository extends JpaRepository<PlanSession, Long> {
    List<PlanSession> findAllByUserId(Long userId);
    Optional<PlanSession> findByIdAndUserId(Long id, Long userId);
    Optional<PlanSession> findByPlanIdAndUserId(Long planId, Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
