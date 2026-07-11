package com.workouttracker.application.workout.plan;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.entities.workout.Plan;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GetPlansUseCase {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public GetPlansUseCase(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public Response execute() {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        List< Plan> plans = this.planRepository.findAllByUserId(userId)
                .stream()
                .sorted((plan, compare) -> plan.getName().compareTo(compare.getName()))
                .toList();

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Consulta exitosa");
        data.put("plans", this.mapPlans(plans));

        return new Response(data, transactionId);
    }

    private List<Map<String, Object>> mapPlans(List<Plan> plans) {
        return plans.stream()
                .map(this::convertToMap)
                .toList();
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
