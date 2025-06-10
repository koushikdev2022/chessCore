package com.iksen.chessCore.serviceImpl.plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.plan.PlanDTO;
import com.iksen.chessCore.mapper.plan.PlanMapper;
import com.iksen.chessCore.model.Plan;
import com.iksen.chessCore.repositary.PlanRepository;
import com.iksen.chessCore.service.plan.PlanService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanServiceImpl implements PlanService{
        @Autowired
        private PlanRepository planRepository;
        @Override
        public List<PlanDTO>  findAllPlan(int id){
            List<Plan> plan = planRepository.findByStatus(1);
            List<PlanDTO> pln = PlanMapper.toDTOList(plan);
            return pln;
        }
}
