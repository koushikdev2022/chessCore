package com.iksen.chessCore.repositary;

import com.iksen.chessCore.model.PlanKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanKeyRepository extends JpaRepository<PlanKey, Long> {
}
