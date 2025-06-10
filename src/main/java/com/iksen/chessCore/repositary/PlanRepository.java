package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.iksen.chessCore.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

       List<Plan> findByStatus(int Status);

}
