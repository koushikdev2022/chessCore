package com.iksen.chessCore.dto.plan;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanPriceDTO {

    private Long id;
    private String currency;
    private String price;
    private Long countryId;
        private String perInterval;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
