package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iksen.chessCore.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
