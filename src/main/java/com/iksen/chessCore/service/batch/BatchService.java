package com.iksen.chessCore.service.batch;

import java.util.List;
import java.util.Optional;

import com.iksen.chessCore.dto.batch.BatchDTO;
import com.iksen.chessCore.model.Batch;


public interface BatchService {
        List<BatchDTO> batches(Long[] ids);
}
