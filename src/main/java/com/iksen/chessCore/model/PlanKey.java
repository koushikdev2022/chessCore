package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "plan_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_price_key", length = 255)
    private String planPriceKey;

    @Column(name = "plan_extrakey_key", length = 255)
    private String planExtrakeyKey;

    @Column(name = "plan_details_id")
    private Long planDetailsId;

    @Column(name = "payment_id")
    private Long paymentId;

    @Column(columnDefinition = "int(11) default 1")
    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}