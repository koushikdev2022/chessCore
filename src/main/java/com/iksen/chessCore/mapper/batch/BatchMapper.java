package com.iksen.chessCore.mapper.batch;



import com.iksen.chessCore.dto.batch.*;
import com.iksen.chessCore.dto.coach.CoachDTO;
import com.iksen.chessCore.dto.day.DayDTO;
import com.iksen.chessCore.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class BatchMapper {

    public static BatchDTO toDTO(Batch batch) {
        if (batch == null) return null;

        return BatchDTO.builder()
                .id(batch.getId())
                .batchName(batch.getBatchName())
                .countryId(batch.getCountryId())
                .isComplete(batch.getIsComplete())
                .rmId(batch.getRmId())
                .studentLimit(batch.getStudentLimit())
                .ohId(batch.getOhId())
                .batchType(batch.getBatchType())
                .durationInteger(batch.getDurationInteger())
                .durationString(batch.getDurationString())
                .startDate(batch.getStartDate())
                .endDate(batch.getEndDate())
                .status(batch.getStatus())
                .coach(toCoachDTO(batch.getCoach()))
                .batchTimeDTOs(toBatchTimeDTOList(batch.getBatcheTimes()))
                .batchTimeClashDTOs(toBatchTimeClashDTOList(batch.getBatcheTimeClash()))
                .batchTimeCompleteDTOs(toBatchTimeCompleteDTOList(batch.getBatcheTimeComplete()))

                .build();
    }

    private static CoachDTO toCoachDTO(Coach coach) {
        if (coach == null) return null;
        return CoachDTO.builder()
                .id(coach.getId())
                .firstName(coach.getFirstName())
                .lastName(coach.getLastName())
                .email(coach.getEmail())
                .mobile(coach.getMobile())
                // Add other fields from CoachDTO as needed
                .build();
    }

    private static BatchTimeDTO toBatchTimeDTO(BatchTime batchTime) {
        if (batchTime == null) return null;
        return BatchTimeDTO.builder()
                .id(batchTime.getId())
        
                .reassignedCoachId(batchTime.getReassignedCoachId())
                .reassignedCoachType(batchTime.getReassignedCoachType())
                .startTime(batchTime.getStartTime())
                .startTimeAm(batchTime.getStartTimeAm())
                .endTime(batchTime.getEndTime())
                .endTimeAm(batchTime.getEndTimeAm())
                .link(batchTime.getLink())
                .linkApp(batchTime.getLinkApp())
                .date(batchTime.getDate())
                .rescheduleStartTime(batchTime.getRescheduleStartTime())
                .rescheduleStartTimeAm(batchTime.getRescheduleStartTimeAm())
                .rescheduleEndTime(batchTime.getRescheduleEndTime())
                .rescheduleEndTimeAm(batchTime.getRescheduleEndTimeAm())
          
                .rescheduleCause(batchTime.getRescheduleCause())
                .rescheduleRequest(batchTime.getRescheduleRequest())
                .cancelCause(batchTime.getCancelCause())
                .cancelRequest(batchTime.getCancelRequest())
                .status(batchTime.getStatus())
                .createdAt(batchTime.getCreatedAt())
                .updatedAt(batchTime.getUpdatedAt())
                .day(toDayDTO(batchTime.getDay()))
                .build();
    }

    private static BatchTimeClashDTO toBatchTimeClashDTO(BatchTimeClash batchTimeClash) {
        if (batchTimeClash == null) return null;
        return BatchTimeClashDTO.builder()
                .id(batchTimeClash.getId())
              
                .reassignedCoachId(batchTimeClash.getReassignedCoachId())
                .reassignedCoachType(batchTimeClash.getReassignedCoachType())
                .startTime(batchTimeClash.getStartTime())
                .startTimeAm(batchTimeClash.getStartTimeAm())
                .endTime(batchTimeClash.getEndTime())
                .endTimeAm(batchTimeClash.getEndTimeAm())
                .link(batchTimeClash.getLink())
                .linkApp(batchTimeClash.getLinkApp())
                .date(batchTimeClash.getDate())
                .rescheduleStartTime(batchTimeClash.getRescheduleStartTime())
                .rescheduleStartTimeAm(batchTimeClash.getRescheduleStartTimeAm())
                .rescheduleEndTime(batchTimeClash.getRescheduleEndTime())
                .rescheduleEndTimeAm(batchTimeClash.getRescheduleEndTimeAm())
                
                .rescheduleCause(batchTimeClash.getRescheduleCause())
                .rescheduleRequest(batchTimeClash.getRescheduleRequest())
                .cancelCause(batchTimeClash.getCancelCause())
                .cancelRequest(batchTimeClash.getCancelRequest())
                .status(batchTimeClash.getStatus())
                .createdAt(batchTimeClash.getCreatedAt())
                .updatedAt(batchTimeClash.getUpdatedAt())
                .day(toDayDTO(batchTimeClash.getDay()))
                .build();
    }

    private static BatchTimeCompleteDTO toBatchTimeCompleteDTO(BatchTimeClash batchTimeComplete) {
        if (batchTimeComplete == null) return null;
        return BatchTimeCompleteDTO.builder()
                .id(batchTimeComplete.getId())
              
                .reassignedCoachId(batchTimeComplete.getReassignedCoachId())
                .reassignedCoachType(batchTimeComplete.getReassignedCoachType())
                .startTime(batchTimeComplete.getStartTime())
                .startTimeAm(batchTimeComplete.getStartTimeAm())
                .endTime(batchTimeComplete.getEndTime())
                .endTimeAm(batchTimeComplete.getEndTimeAm())
                .link(batchTimeComplete.getLink())
                .linkApp(batchTimeComplete.getLinkApp())
                .date(batchTimeComplete.getDate())
                .rescheduleStartTime(batchTimeComplete.getRescheduleStartTime())
                .rescheduleStartTimeAm(batchTimeComplete.getRescheduleStartTimeAm())
                .rescheduleEndTime(batchTimeComplete.getRescheduleEndTime())
                .rescheduleEndTimeAm(batchTimeComplete.getRescheduleEndTimeAm())
             
                .rescheduleCause(batchTimeComplete.getRescheduleCause())
                .rescheduleRequest(batchTimeComplete.getRescheduleRequest())
                .cancelCause(batchTimeComplete.getCancelCause())
                .cancelRequest(batchTimeComplete.getCancelRequest())
                .status(batchTimeComplete.getStatus())
                .createdAt(batchTimeComplete.getCreatedAt())
                .updatedAt(batchTimeComplete.getUpdatedAt())
                .day(toDayDTO(batchTimeComplete.getDay()))
                .build();
    }

    private static DayDTO toDayDTO(Day day) {
        if (day == null) return null;
        return DayDTO.builder()
                .id(day.getId())
                .day(day.getDay())
                // Add other fields if present
                .build();
    }

    private static <T> T singleOrNull(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return list.get(0); // You can change this logic if you want a different default
    }

    public static List<BatchDTO> toDTOList(List<Batch> batches) {
        return batches.stream().map(BatchMapper::toDTO).collect(Collectors.toList());
    }

    private static List<BatchTimeDTO> toBatchTimeDTOList(List<BatchTime> list) {
        if (list == null) return null;
        return list.stream().map(BatchMapper::toBatchTimeDTO).collect(Collectors.toList());
    }

    private static List<BatchTimeClashDTO> toBatchTimeClashDTOList(List<BatchTimeClash> list) {
        if (list == null) return null;
        return list.stream().map(BatchMapper::toBatchTimeClashDTO).collect(Collectors.toList());
    }

    private static List<BatchTimeCompleteDTO> toBatchTimeCompleteDTOList(List<BatchTimeClash> list) {
        if (list == null) return null;
        return list.stream().map(BatchMapper::toBatchTimeCompleteDTO).collect(Collectors.toList());
    }
}
