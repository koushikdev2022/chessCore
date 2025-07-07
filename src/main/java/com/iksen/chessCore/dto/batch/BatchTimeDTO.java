package com.iksen.chessCore.dto.batch;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.iksen.chessCore.dto.day.DayDTO;

import lombok.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchTimeDTO {

    private Long id;
    private Long batchId;
    private Long reassignedCoachId;
    private Integer reassignedCoachType;
    private LocalTime startTime;
    private Integer startTimeAm;
    private LocalTime endTime;
    private Integer endTimeAm;
    private String link;
    private String linkApp;
    private LocalDate date;
    private LocalTime rescheduleStartTime;
    private Integer rescheduleStartTimeAm;
    private LocalTime rescheduleEndTime;
    private Integer rescheduleEndTimeAm;
    private Integer dayId;
    private String rescheduleCause;
    private Integer rescheduleRequest;
    private String cancelCause;
    private Integer cancelRequest;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private DayDTO day;
    
}

