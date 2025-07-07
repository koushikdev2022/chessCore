package com.iksen.chessCore.mapper.batch;


import com.iksen.chessCore.dto.batch.UserBatchMapDTO;
import com.iksen.chessCore.model.UserBatchMap;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserBatchMapMapper {

    // Entity → DTO
    public static UserBatchMapDTO toDTO(UserBatchMap userBatchMap) {
        if (userBatchMap == null) return null;

        return UserBatchMapDTO.builder()
                .id(userBatchMap.getId())
                .batchId(userBatchMap.getBatchId())
                .userId(userBatchMap.getUserId())
                .status(userBatchMap.getStatus())
                .createdAt(userBatchMap.getCreatedAt())
                .updatedAt(userBatchMap.getUpdatedAt())
                .build();
    }

    // DTO → Entity
    public static UserBatchMap toEntity(UserBatchMapDTO dto) {
        if (dto == null) return null;

        UserBatchMap entity = new UserBatchMap();
        entity.setId(dto.getId());
        entity.setBatchId(dto.getBatchId());
        entity.setUserId(dto.getUserId());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        return entity;
    }

    // List<Entity> → List<DTO>
    public static List<UserBatchMapDTO> toDTOList(List<UserBatchMap> entities) {
        if (entities == null) return Collections.emptyList();

        return entities.stream()
                .map(UserBatchMapMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Optional<Entity> → DTO
    public static UserBatchMapDTO fromOptional(Optional<UserBatchMap> optional) {
        return optional.map(UserBatchMapMapper::toDTO).orElse(null);
    }
}
