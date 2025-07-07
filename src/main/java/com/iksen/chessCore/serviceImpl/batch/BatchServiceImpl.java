package com.iksen.chessCore.serviceImpl.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.batch.BatchDTO;
import com.iksen.chessCore.dto.paymentMethod.PaymentMethodKeyDTO;
import com.iksen.chessCore.mapper.batch.BatchMapper;
import com.iksen.chessCore.mapper.paymentMethod.PaymentMethodKeyMapper;
import com.iksen.chessCore.model.Batch;
import com.iksen.chessCore.repositary.BatchRepository;
import com.iksen.chessCore.repositary.PaymentMethodKeyRepository;
import com.iksen.chessCore.service.batch.BatchService;

@Service
public class BatchServiceImpl implements BatchService{
        @Autowired
        private BatchRepository batchRepository;
        public  List<BatchDTO> batches(Long[] ids){
            List<Batch> batchs =  batchRepository.findAllByIdIn(ids);
            return BatchMapper.toDTOList(batchs);
        }
        public List<BatchDTO> batchesWithPagination(Long[] ids, int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<Batch> pageResult = batchRepository.findAllByIdIn(ids, pageable);
            return BatchMapper.toDTOList(pageResult.getContent()); 
        }
        public  List<BatchDTO> batchesWithId(Long[] ids){
            List<Batch> batchs =  batchRepository.findAllByIdIn(ids);
            return BatchMapper.toDTOList(batchs);
        }
        
}
