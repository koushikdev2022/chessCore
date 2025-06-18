package com.iksen.chessCore.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {

    private Integer id;
    private String planName;
    private String planShortName;
    private String planDescription;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String avatar;

    private List<PlanPriceDTO> prices;
    private List<PlanAccessMapDTO> accessMappings;
}