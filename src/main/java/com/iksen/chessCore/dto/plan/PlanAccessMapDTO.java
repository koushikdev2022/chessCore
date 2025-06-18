package com.iksen.chessCore.dto.plan;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanAccessMapDTO {
    private Integer id;         
    private String desc;      
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PlanAccessDTO access; 
}