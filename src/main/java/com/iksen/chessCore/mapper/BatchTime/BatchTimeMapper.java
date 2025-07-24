package com.iksen.chessCore.mapper.BatchTime;

import com.iksen.chessCore.dto.batch.BatchTimeDTO;
import com.iksen.chessCore.dto.day.DayDTO;
import com.iksen.chessCore.model.BatchTime;
import com.iksen.chessCore.model.Day;

import java.util.List;
import java.util.stream.Collectors;

public class BatchTimeMapper {

    public static BatchTimeDTO toDTO(BatchTime entity) {
        if (entity == null) return null;

        return BatchTimeDTO.builder()
                .id(entity.getId())
                .batchId(entity.getBatch() != null ? entity.getBatch().getId() : null)
                .reassignedCoachId(entity.getReassignedCoachId())
                .reassignedCoachType(entity.getReassignedCoachType())
                .startTime(entity.getStartTime())
                .startTimeAm(entity.getStartTimeAm())
                .endTime(entity.getEndTime())
                .endTimeAm(entity.getEndTimeAm())
                .link(entity.getLink())
                .linkApp(entity.getLinkApp())
                .date(entity.getDate())
                .rescheduleStartTime(entity.getRescheduleStartTime())
                .rescheduleStartTimeAm(entity.getRescheduleStartTimeAm())
                .rescheduleEndTime(entity.getRescheduleEndTime())
                .rescheduleEndTimeAm(entity.getRescheduleEndTimeAm())
               
                .rescheduleCause(entity.getRescheduleCause())
                .rescheduleRequest(entity.getRescheduleRequest())
                .cancelCause(entity.getCancelCause())
                .cancelRequest(entity.getCancelRequest())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .day(toDayDTO(entity.getDay()))
                .build();
    }

    public static List<BatchTimeDTO> toDTOList(List<BatchTime> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(BatchTimeMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static DayDTO toDayDTO(Day day) {
        if (day == null) return null;

        return DayDTO.builder()
                .id(day.getId())
           
                .build();
    }
}
