package com.workouttracker.domain.entities.workout;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plans_exercises")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Integer sets;

    @Column(nullable = true)
    private Integer repetitions;

    @Column(nullable = true)
    private Integer duration;

    @Column(nullable = true)
    private Double weights;

    @Column(nullable = true)
    private Integer exerciseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
