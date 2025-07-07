package com.iksen.chessCore.service.batch;

import java.util.List;

import com.iksen.chessCore.dto.batch.UserBatchMapDTO;

public interface UserBatchMapService {
        List<UserBatchMapDTO> totalBatch(Long id);
}
