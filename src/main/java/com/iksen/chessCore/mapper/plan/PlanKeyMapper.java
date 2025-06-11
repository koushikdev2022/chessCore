package com.iksen.chessCore.mapper.plan;


import com.iksen.chessCore.dto.plan.PlanKeyDTO;
import com.iksen.chessCore.model.PlanKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlanKeyMapper {

    public PlanKeyDTO toDTO(PlanKey planKey) {
        return new PlanKeyDTO(
                planKey.getId(),
                planKey.getPlanPriceKey(),
                planKey.getPlanExtrakeyKey(),
                planKey.getPlanDetailsId(),
                planKey.getPaymentId(),
                planKey.getStatus(),
                planKey.getCreatedAt(),
                planKey.getUpdatedAt()
        );
    }

    public List<PlanKeyDTO> toDTOList(List<PlanKey> planKeys) {
        return planKeys.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PlanKeyDTO> toDTOOp(Optional<PlanKey> planKey) {
        return planKey.map(this::toDTO);
    }
}
