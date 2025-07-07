package com.iksen.chessCore.dto.batch;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBatchMapDTO {
    private Long id;
    private Long batchId;
    private Long userId;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
