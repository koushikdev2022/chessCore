package com.iksen.chessCore.serviceImpl.batchTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.batch.BatchTimeDTO;
import com.iksen.chessCore.mapper.BatchTime.BatchTimeMapper;
import com.iksen.chessCore.model.BatchTime;
import com.iksen.chessCore.repositary.BatchTimeRepository;
import com.iksen.chessCore.service.batchTime.BatchTimeService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BatchTimeServiceImpl implements BatchTimeService{
        @Autowired
        private BatchTimeRepository batchTimeRepository;
        public BatchTimeDTO batchTimeWithId(Long id) {
            BatchTime data = batchTimeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BatchTime not found with ID: " + id));
            
            return BatchTimeMapper.toDTO(data); 
        }
}
