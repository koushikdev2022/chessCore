package com.iksen.chessCore.dto.plan;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanAccessDTO {

    private Integer id;
    private String accessName;
    private String planDescription;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
