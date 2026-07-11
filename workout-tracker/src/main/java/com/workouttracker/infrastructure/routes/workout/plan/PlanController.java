package com.workouttracker.infrastructure.routes.workout.plan;

import com.workouttracker.application.workout.plan.*;
import com.workouttracker.domain.dto.response.Response;
import com.workouttracker.domain.dto.workout.request.PlanRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("workout-management/api/v1")
public class PlanController {
    private final CreatePlanUseCase createPlanUseCase;
    private final GetPlansUseCase getPlansUseCase;
    private final GetPlanUseCase getPlanUseCase;
    private final UpdatePlanUseCase updatePlanUseCase;
    private final DeletePlanUseCase deletePlanUseCase;

    public PlanController(CreatePlanUseCase createPlanUseCase,
                          GetPlansUseCase getPlansUseCase,
                          GetPlanUseCase getPlanUseCase,
                          UpdatePlanUseCase updatePlanUseCase,
                          DeletePlanUseCase deletePlanUseCase) {
        this.createPlanUseCase = createPlanUseCase;
        this.getPlansUseCase = getPlansUseCase;
        this.getPlanUseCase = getPlanUseCase;
        this.updatePlanUseCase = updatePlanUseCase;
        this.deletePlanUseCase = deletePlanUseCase;
    }

    @PostMapping("/plans")
    public ResponseEntity<Response> plans(@Valid @RequestBody PlanRequest request) {
        Response response = this.createPlanUseCase.execute(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/plans")
    public ResponseEntity<Response> plans() {
        Response response = this.getPlansUseCase.execute();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<Response> plan(@PathVariable("id") Long planId) {
        Response response = this.getPlanUseCase.execute(planId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/plans/{id}")
    public ResponseEntity<Response> updatePlan(@PathVariable("id") Long planId,
                                               @Valid @RequestBody PlanRequest request) {
        Response response = this.updatePlanUseCase.execute(planId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/plans/{id}")
    public ResponseEntity<Response> deletePlan(@PathVariable("id") Long planId) {
        Response response = this.deletePlanUseCase.execute(planId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
