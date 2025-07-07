

package com.iksen.chessCore.repositary;

import com.iksen.chessCore.model.Batch;
import com.iksen.chessCore.model.BatchTime;
import com.iksen.chessCore.model.BatchTimeClash;
import com.iksen.chessCore.model.BatchTimeComplete;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iksen.chessCore.model.BatchTime;

@Repository
public interface BatchTimeCompleteRepository extends JpaRepository<BatchTimeComplete, Long> {
    
}