package com.workouttracker.infrastructure.routes.workout.plansession;

import com.workouttracker.application.workout.plansession.*;
import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.dto.workout.request.PlanSessionRequest;
import com.workouttracker.domain.dto.workout.request.PlanSessionStatusRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("workout-management/api/v1")
public class PlanSessionController {
    private final CreatePlanSessionUseCase createPlanSessionUseCase;
    private final GetPlanSessionsUseCase getPlanSessionsUseCase;
    private final UpdatePlanSessionUseCase updatePlanSessionUseCase;
    private final UpdatePlanSessionStatusUseCase updatePlanSessionStatusUseCase;
    private final DeletePlanSessionUseCase deletePlanSessionUseCase;

    public PlanSessionController(CreatePlanSessionUseCase createPlanSessionUseCase,
                                 GetPlanSessionsUseCase getPlanSessionsUseCase,
                                 UpdatePlanSessionUseCase updatePlanSessionUseCase,
                                 UpdatePlanSessionStatusUseCase updatePlanSessionStatusUseCase,
                                 DeletePlanSessionUseCase deletePlanSessionUseCase) {
        this.createPlanSessionUseCase = createPlanSessionUseCase;
        this.getPlanSessionsUseCase = getPlanSessionsUseCase;
        this.updatePlanSessionUseCase = updatePlanSessionUseCase;
        this.updatePlanSessionStatusUseCase = updatePlanSessionStatusUseCase;
        this.deletePlanSessionUseCase = deletePlanSessionUseCase;
    }

    @PostMapping("/plans/sessions")
    public ResponseEntity<Response> planSession(@Valid @RequestBody PlanSessionRequest request) {
        Response response = this.createPlanSessionUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/plans/sessions")
    public ResponseEntity<Response> planSession() {
        Response response = this.getPlanSessionsUseCase.execute();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/plans/sessions/{id}")
    public ResponseEntity<Response> planSession(@PathVariable  Long id,
                                                @Valid @RequestBody PlanSessionRequest request) {
        Response response = this.updatePlanSessionUseCase.execute(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/plans/sessions/{id}/status")
    public ResponseEntity<Response> planSession(@PathVariable Long id,
                                                @Valid @RequestBody PlanSessionStatusRequest request) {
        Response response = this.updatePlanSessionStatusUseCase.execute(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/plans/sessions/{id}")
    public ResponseEntity<Response> planSession(@PathVariable  Long id) {
        Response response = this.deletePlanSessionUseCase.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}