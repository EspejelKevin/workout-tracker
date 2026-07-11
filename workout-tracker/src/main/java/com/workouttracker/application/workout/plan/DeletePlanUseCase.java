package com.workouttracker.application.workout.plan;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DeletePlanUseCase {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public DeletePlanUseCase(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Response execute(Long planId) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        this.planRepository.findByIdAndUserId(planId, userId)
                .orElseThrow(() -> new RuntimeException("Plan not found: " + planId));

        this.planRepository.deleteByIdAndUserId(planId, userId);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Eliminacion exitosa");

        return new Response(data, transactionId);
    }
}
