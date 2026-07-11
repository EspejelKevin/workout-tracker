package com.workouttracker.application.workout.plan;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.dto.workout.request.PlanRequest;
import com.workouttracker.domain.entities.workout.Plan;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UpdatePlanUseCase {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public UpdatePlanUseCase(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public Response execute(Long planId, PlanRequest request) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Plan plan = this.planRepository.findByIdAndUserId(planId, userId)
                .orElseThrow(() -> new RuntimeException("Plan not found: " + planId));

        plan.setName(request.getName());
        plan.setDescription(request.getDescription());

        if (request.getComments() != null && !request.getComments().isEmpty()) {
            plan.setComments(request.getComments());
        }

        Plan planUpdated = this.planRepository.save(plan);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Creacion exitosa");
        data.put("plan", this.convertToMap(planUpdated));

        return new Response(data, transactionId);
    }

    private Map<String, Object> convertToMap(Plan plan) {
        return Map.ofEntries(
                Map.entry("id", plan.getId()),
                Map.entry("name", plan.getName()),
                Map.entry("description", plan.getDescription()),
                Map.entry("comments", plan.getComments() == null ? "" : plan.getComments()),
                Map.entry("plan_exercises", plan.getPlanExercises())
        );
    }
}
