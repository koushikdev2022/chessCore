package com.iksen.chessCore.dto.day;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DayDTO {
    private Long id;
    private String day;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

