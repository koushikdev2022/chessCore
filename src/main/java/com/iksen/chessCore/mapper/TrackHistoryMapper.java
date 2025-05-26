package com.iksen.chessCore.mapper;

import com.iksen.chessCore.dto.auth.logTracking.UserHistoryDTO;
import com.iksen.chessCore.model.UserHistory;

public class TrackHistoryMapper {
        public static UserHistory toEntity(UserHistoryDTO dto) {
            if (dto == null) return null;
            return UserHistory.builder()
                    .id(dto.getId())
                    .browser(dto.getBrowser())
                    .ip(dto.getIp())
                    .lat(dto.getLat())
                    .longitude(dto.getLongitude())
                    .deviceId(dto.getDeviceId())
                    .deviceName(dto.getDeviceName())
                    .userFlag(UserHistory.UserFlag.valueOf(dto.getUserFlag()))
                    .userId(dto.getUserId())
                    .createdAt(dto.getCreatedAt())
                    .updatedAt(dto.getUpdatedAt())
                    .build();
        }
        public static UserHistoryDTO toDTO(UserHistory entity) {
            if (entity == null) return null;

            return UserHistoryDTO.builder()
                    .id(entity.getId())
                    .browser(entity.getBrowser())
                    .ip(entity.getIp())
                    .lat(entity.getLat())
                    .longitude(entity.getLongitude())
                    .deviceId(entity.getDeviceId())
                    .deviceName(entity.getDeviceName())
                    .userFlag(entity.getUserFlag() != null ? entity.getUserFlag().name() : null)
                    .userId(entity.getUser() != null ? entity.getUser().getId() : entity.getUserId()) // handles both cases
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .build();
        }

}
