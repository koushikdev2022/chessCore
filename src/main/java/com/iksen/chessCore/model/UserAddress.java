package com.iksen.chessCore.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_address")
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "state_id", nullable = false)
    private Long stateId;

    @Column(name = "first_line", nullable = false)
    private String firstLine;

    @Column(name = "second_line")
    private String secondLine;

    @Column(name = "zip")
    private String zip;

    @Column(name = "area_no")
    private String areaNo;

    @Column(name = "flat_no")
    private String flatNo;

    @Column(name = "post")
    private String post;

    @Column(name = "ps")
    private String ps;

    @Column(name = "lat")
    private String lat;

    @Column(name = "longitude")
    private String longitude;

    @Builder.Default
    @Column(name = "status")
    private Integer status = 1;

    @Builder.Default
    @Column(name = "is_primary")
    private Integer isPrimary = 1;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "user_id", insertable = false, updatable = false)
    // private User user;


    @Column(name = "user_id", nullable = false)
    private Long userId; // <-- Add this

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}

