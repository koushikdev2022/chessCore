package com.iksen.chessCore.serviceImpl.plan;


import com.iksen.chessCore.dto.plan.PlanKeyDTO;
import com.iksen.chessCore.model.PlanKey;
import com.iksen.chessCore.repositary.PlanKeyRepository;
import com.iksen.chessCore.service.plan.PlanKeyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanKeyServiceImpl implements PlanKeyService {

    @Autowired
    private PlanKeyRepository planKeyRepository;

    @Autowired
    private com.iksen.chessCore.mapper.plan.PlanKeyMapper planKeyMapper;

    @Override
    public List<PlanKeyDTO> getAllPlanKeys() {
        List<PlanKey> entities = planKeyRepository.findAll();
        return planKeyMapper.toDTOList(entities);
    }

    @Override
    public Optional<PlanKeyDTO> getPlanKeyById(Long id) {
        return planKeyMapper.toDTOOp(planKeyRepository.findById(id));
    }
}