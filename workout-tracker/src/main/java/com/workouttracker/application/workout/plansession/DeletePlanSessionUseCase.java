package com.workouttracker.application.workout.plansession;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanSessionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DeletePlanSessionUseCase {
    private final PlanSessionRepository planSessionRepository;
    private final UserRepository userRepository;

    public DeletePlanSessionUseCase(PlanSessionRepository planSessionRepository,
                                    UserRepository userRepository) {
        this.planSessionRepository = planSessionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Response execute(Long planSessionId) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName() : "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        this.planSessionRepository.findByIdAndUserId(planSessionId, userId)
                .orElseThrow(() ->
                        new RuntimeException("PlanSession not found with id: " + planSessionId));

        this.planSessionRepository.deleteByIdAndUserId(planSessionId, userId);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Eliminacion exitosa");

        return new Response(data, transactionId);
    }
}
