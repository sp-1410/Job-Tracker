package com.jobtrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "online_assessments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OnlineAssessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private Application application;

    private String platform;
    private LocalDate assessmentDate;
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OaResult result = OaResult.PENDING;

    private Double score;

    @Column(length = 2000)
    private String topics;

    @Column(length = 5000)
    private String notes;
}
