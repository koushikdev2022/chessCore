package com.iksen.chessCore.service.plan;



import java.util.List;
import java.util.Optional;

import com.iksen.chessCore.dto.plan.PlanKeyDTO;

public interface PlanKeyService {
    List<PlanKeyDTO> getAllPlanKeys();
    Optional<PlanKeyDTO> getPlanKeyById(Long id);
}