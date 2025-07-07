package com.iksen.chessCore.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iksen.chessCore.model.BatchTime;

@Repository
public interface BatchTimeRepository extends JpaRepository<BatchTime, Long> {
    
}