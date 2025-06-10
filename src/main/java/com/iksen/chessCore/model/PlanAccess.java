package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "plan_accesses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "planMappings")
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

    // Keep LAZY and initialize collection
    @OneToMany(mappedBy = "access", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("access-mappings")
    private List<PlanAccessMap> planMappings = new ArrayList<>();
}