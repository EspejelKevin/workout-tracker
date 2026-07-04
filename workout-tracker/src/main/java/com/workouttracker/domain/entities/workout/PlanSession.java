package com.workouttracker.domain.entities.workout;

import com.workouttracker.domain.entities.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "plan_sessions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime scheduleTime;

    private String status = "PENDING";
    private LocalDateTime startTime = null;
    private LocalDateTime endTime = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
