package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iksen.chessCore.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
        @Query("SELECT DISTINCT p FROM Plan p LEFT JOIN FETCH p.prices pr WHERE p.status = 1 AND (pr.countryId = :countryId )")
        List<Plan> findActivePlans(@Param("countryId") int countryId);
        List<Plan> findByStatus(int Status);

}
