package com.workouttracker.application.workout.plansession;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.dto.workout.request.PlanSessionStatusRequest;
import com.workouttracker.domain.entities.workout.PlanSession;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanSessionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UpdatePlanSessionStatusUseCase {
    private final PlanSessionRepository planSessionRepository;
    private final UserRepository userRepository;

    public UpdatePlanSessionStatusUseCase(PlanSessionRepository planSessionRepository,
                                          UserRepository userRepository) {
        this.planSessionRepository = planSessionRepository;
        this.userRepository = userRepository;
    }

    public Response execute(Long planSessionId, PlanSessionStatusRequest request) {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        PlanSession planSession = this.planSessionRepository.findByIdAndUserId(planSessionId, userId)
                .orElseThrow(() ->
                        new RuntimeException("PlanSession not found with id: " + planSessionId));

        String planSessionStatus = planSession.getStatus();
        String status = request.getStatus();

        if ("COMPLETED".equals(planSessionStatus)) {
            throw new RuntimeException("PlanSession is already COMPLETED and cannot be modified");
        }

        if ("PENDING".equals(planSessionStatus) && status.equals("DOING")) {
            planSession.setStartTime(LocalDateTime.now(ZoneId.of("America/Mexico_City")));
            planSession.setStatus(status);
        } else if ("DOING".equals(planSessionStatus) && status.equals("COMPLETED")) {
            planSession.setEndTime(LocalDateTime.now(ZoneId.of("America/Mexico_City")));
            planSession.setStatus(status);
        } else {
            throw new RuntimeException(
                    String.format("Invalid status transition from %s to %s", planSessionStatus, status));
        }

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
