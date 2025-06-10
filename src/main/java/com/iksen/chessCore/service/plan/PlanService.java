package com.iksen.chessCore.service.plan;

import java.util.List;

import com.iksen.chessCore.dto.plan.PlanDTO;

public interface PlanService {
        List<PlanDTO>  findAllPlan(int id);
}
