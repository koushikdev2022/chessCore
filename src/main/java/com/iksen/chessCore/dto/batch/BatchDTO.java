package com.iksen.chessCore.dto.batch;

import java.time.LocalDate;

import com.iksen.chessCore.dto.coach.CoachDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {

    private Long id;
    private String batchName;
    private Long countryId;
    private Integer isComplete;
    private Long rmId;
    private Integer studentLimit;
    private Long ohId;
    private Integer batchType;
    private Integer durationInteger;
    private Integer durationString;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer status;

    private CoachDTO coach;
    private BatchTimeDTO batchTimeDTO;
    private BatchTimeClashDTO batchTimeClashDTO;
    private BatchTimeCompleteDTO batchTimeCompleteDTO;
   
}

