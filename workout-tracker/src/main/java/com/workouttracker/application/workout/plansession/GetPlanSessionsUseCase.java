package com.workouttracker.application.workout.plansession;

import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.entities.workout.PlanSession;
import com.workouttracker.domain.repositories.auth.UserRepository;
import com.workouttracker.domain.repositories.workout.PlanSessionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class GetPlanSessionsUseCase {
    private final PlanSessionRepository planSessionRepository;
    private final UserRepository userRepository;

    public GetPlanSessionsUseCase(PlanSessionRepository planSessionRepository,
                                  UserRepository userRepository) {
        this.planSessionRepository = planSessionRepository;
        this.userRepository = userRepository;
    }

    public Response execute() {
        String transactionId = UUID.randomUUID().toString();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication != null ? authentication.getName(): "0");

        this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        List<PlanSession> planSessions = this.planSessionRepository.findAllByUserId(userId);

        Map<String, Object> data = new HashMap<>();
        data.put("message", "Consulta exitosa");
        data.put("plan_sessions", this.mapPlanSessions(planSessions));

        return new Response(data, transactionId);
    }

    private List<Map<String, Object>> mapPlanSessions(List<PlanSession> planSessions) {
        return planSessions.stream()
                .map(this::convertToMap)
                .sorted((planSessionToCompare, planSession) -> {
                    LocalDateTime scheduleTimeToCompare = (LocalDateTime) planSessionToCompare.get("schedule_time");
                    LocalDateTime scheduleTime = (LocalDateTime) planSession.get("schedule_time");
                    return scheduleTime.compareTo(scheduleTimeToCompare);
                })
                .toList();
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
