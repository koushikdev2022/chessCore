package com.iksen.chessCore.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanKeyDTO {
    private Long id;
    private String planPriceKey;
    private String planExtrakeyKey;
    private Long planDetailsId;
    private Long paymentId;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

