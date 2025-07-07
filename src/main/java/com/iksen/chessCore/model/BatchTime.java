package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "batch_times")
@Builder
public class BatchTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", insertable = false, updatable = false)
    private Batch batch;


    @Column(name = "reassigned_coach_id", nullable = false)
    private Long reassignedCoachId;

    @Column(name = "reassigned_coach_type", nullable = false)
    private Integer reassignedCoachType;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "start_time_am", nullable = false)
    @Builder.Default
    private Integer startTimeAm = 1;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "end_time_am", nullable = false)
    @Builder.Default
    private Integer endTimeAm = 1;

    @Column(name = "link")
    private String link;

    @Column(name = "link_app")
    private String linkApp;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "resedule_start_time")
    private LocalTime rescheduleStartTime;

    @Column(name = "resedule_start_time_am")
    @Builder.Default
    private Integer rescheduleStartTimeAm = 1;

    @Column(name = "resedule_end_time")
    private LocalTime rescheduleEndTime;

    @Column(name = "resedule_end_time_am")
    @Builder.Default
    private Integer rescheduleEndTimeAm = 1;

    // @Column(name = "day_id", nullable = false)
    // private Integer dayId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("batcheTimes")
    private Day day;

    @Column(name = "reschedule_cause")
    private String rescheduleCause;

    @Column(name = "reschedule_request")
    private Integer rescheduleRequest;

    @Column(name = "cancel_cause")
    private String cancelCause;

    @Column(name = "cancel_request")
    private Integer cancelRequest;

    @Column(name = "status")
    @Builder.Default
    private Integer status = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters and Setters
}
