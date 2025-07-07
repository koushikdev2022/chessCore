package com.iksen.chessCore.repositary;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iksen.chessCore.model.UserBatchMap;

@Repository
public interface UserBatchMapRepository extends JpaRepository<UserBatchMap, Long> {
    List<UserBatchMap> findAllByUserId(Long userId);
}
