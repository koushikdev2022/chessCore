package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "plan_accesses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "access_name", length = 255)
    private String accessName;

    @Lob
    @Column(name = "plan_description")
    private String planDescription;

    @Column(nullable = true)
    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "access", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanAccessMap> planMappings;
}
