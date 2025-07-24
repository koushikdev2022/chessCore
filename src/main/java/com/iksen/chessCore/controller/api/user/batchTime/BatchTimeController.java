package com.iksen.chessCore.controller.api.user.batchTime;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.batch.BatchDTO;
import com.iksen.chessCore.dto.batch.BatchTimeDTO;
import com.iksen.chessCore.dto.batch.UserBatchMapDTO;
import com.iksen.chessCore.dto.paginate.PaginateDTO;
import com.iksen.chessCore.serviceImpl.batchTime.BatchTimeServiceImpl;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/batch")
public class BatchTimeController {

        @Autowired
        private BatchTimeServiceImpl batchTimeServiceImpl;
        @GetMapping("/batchtime")
        public ResponseEntity<?> batchListChild( @RequestParam @NotNull(message = "The 'id' parameter is required") Long id){
            try{
                BatchTimeDTO data = batchTimeServiceImpl.batchTimeWithId(id);
                return ResponseEntity.ok(Map.of(
                    "status", true,
                    "message", "batch found",
                    "status_code", 200,
                    "data",data
                ));
            }catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(400).body(Map.of(
                    "status", false,
                    "message", "Internal server error: " + e.getMessage(),
                    "status_code", 400
                ));
            }
        }
}
