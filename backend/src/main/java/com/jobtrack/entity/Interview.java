package com.jobtrack.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Interview {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    private String roundName;
    private String interviewType;
    private LocalDateTime scheduledAt;
    private String meetingLink;
    private String status;

    @Column(length = 2000)
    private String topics;

    @Column(length = 5000)
    private String notes;

    @Column(length = 5000)
    private String feedback;

    @Column(length = 5000)
    private String preparationChecklist;
}
