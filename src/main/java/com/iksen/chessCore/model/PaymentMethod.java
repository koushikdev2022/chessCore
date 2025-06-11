package com.iksen.chessCore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "payment_method")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("status = 1")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String name;

    @Column(name = "short_name", length = 255)
    private String shortName;

    @Column(name = "avatar", length = 255)
    private String avatar;

    @Column(columnDefinition = "int(11) default 1")
    private Integer status = 1;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<PaymentCountryMap> paymentCountryMaps;
}