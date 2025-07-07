package com.iksen.chessCore.controller.api.user.batch;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.auth.LoginDTO;
import com.iksen.chessCore.dto.batch.BatchDTO;
import com.iksen.chessCore.dto.batch.UserBatchMapDTO;
import com.iksen.chessCore.dto.paginate.PaginateDTO;
import com.iksen.chessCore.mapper.batch.BatchMapper;
import com.iksen.chessCore.model.Batch;
import com.iksen.chessCore.model.UserBatchMap;
import com.iksen.chessCore.serviceImpl.batch.BatchServiceImpl;
import com.iksen.chessCore.serviceImpl.batch.UserBatchMapServiceImpl;
import com.iksen.chessCore.utill.JwtUtill;

@RestController
@RequestMapping("/api/batch")
public class BatchController {
        @Autowired
        private BatchServiceImpl batchServiceImpl;
        @Autowired
        private JwtUtill jwtUtill;
        @Autowired
        private UserBatchMapServiceImpl userBatchMapServiceImpl;
        @PostMapping("/list")
        public ResponseEntity<?> batchList(@RequestBody(required = false) PaginateDTO paginateDTO){
                Long userId = jwtUtill.getCurrentUserId();
                List<UserBatchMapDTO> totalBatch =  userBatchMapServiceImpl.totalBatch(userId);
                Long[] batchIdArray = totalBatch.stream()
                        .map(UserBatchMapDTO::getBatchId)
                        .toArray(Long[]::new);
                List<BatchDTO> batchEntities;

                if (paginateDTO != null) {
                    int page = paginateDTO.getPage();
                    int size = paginateDTO.getLimit();
                    batchEntities = batchServiceImpl.batchesWithPagination(batchIdArray, page, size);
                } else {
                    batchEntities = batchServiceImpl.batches(batchIdArray);
                }

   
                return ResponseEntity.ok(Map.of(
                    "status", true,
                    "message", "batch found",
                    "status_code", 200,
                    "batch",batchEntities
                ));
        }
}
