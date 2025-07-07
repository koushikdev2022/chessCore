package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "batches")
@Builder
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batch_name", nullable = false)
    private String batchName;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "is_complete", nullable = false)
    private Integer isComplete = 0;

    @Column(name = "rm_id", nullable = false)
    private Long rmId = 0L;

    @Column(name = "student_limit")
    private Integer studentLimit = 0;

    @Column(name = "oh_id", nullable = false)
    private Long ohId;

    @Column(name = "batch_type", nullable = false)
    private Integer batchType;

    @Column(name = "duration_integer", nullable = false)
    private Integer durationInteger;

    @Column(name = "duration_string", nullable = false)
    private Integer durationString;

    // @Column(name = "coach_id", nullable = false)
    // private Long coachId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("batches") 
    private Coach coach;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Getters and Setters
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BatchTime> batcheTimes;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BatchTimeClash> batcheTimeClash;

    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BatchTimeClash> batcheTimeComplete;
  
}
