package com.iksen.chessCore.repositary;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iksen.chessCore.model.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findAllByIdIn(Long[] ids);
    Page<Batch> findAllByIdIn(Long[] ids, Pageable pageable);
}
