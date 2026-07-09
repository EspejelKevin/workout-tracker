package com.workouttracker.infrastructure.repositories.workout.plan;

import com.workouttracker.domain.entities.workout.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MySQLRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByUserId(Long userId);
    Optional<Plan> findByIdAndUserId(Long id, Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
