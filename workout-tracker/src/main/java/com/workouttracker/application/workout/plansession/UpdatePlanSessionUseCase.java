package com.workouttracker.application.workout.plansession;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.dto.workout.request.PlanSessionRequest;
import com.workouttracker.domain.entities.workout.Plan;
import com.workouttracker.domain.entities.workout.PlanSession;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanRepository;
import com.workouttracker.domain.repositories.workout.PlanSessionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UpdatePlanSessionUseCase {
    private final PlanSessionRepository planSessionRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    public UpdatePlanSessionUseCase(PlanSessionRepository planSessionRepository,
                                    PlanRepository planRepository,
                                    UserRepository userRepository) {
        this.planSessionRepository = planSessionRepository;
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public Response execute(Long planSessionId, PlanSessionRequest request) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        Plan plan = this.planRepository.findByIdAndUserId(request.getPlanId(), userId)
                .orElseThrow(() -> new RuntimeException("Plan does not exist: " + request.getPlanId()));

        PlanSession planSession = this.planSessionRepository.findByIdAndUserId(planSessionId, userId)
                .orElseThrow(() ->
                    new RuntimeException("PlanSession not found with id: " + planSessionId));


        planSession.setScheduleTime(request.getScheduleTime());
        planSession.setPlan(plan);

        planSession = this.planSessionRepository.save(planSession);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Actualizacion exitosa");
        data.put("plan_session", this.convertToMap(planSession));

        return new Response(data, transactionId);
    }

    private Map<String, Object> convertToMap(PlanSession planSession) {
        return Map.ofEntries(
                Map.entry("id", planSession.getId()),
                Map.entry("schedule_time", planSession.getScheduleTime()),
                Map.entry("status", planSession.getStatus()),
                Map.entry("start_time", planSession.getStartTime() == null ? "": planSession.getStartTime()),
                Map.entry("end_time", planSession.getEndTime() == null ? "": planSession.getEndTime()),
                Map.entry("plan_id", planSession.getPlan().getId())
        );
    }
}
