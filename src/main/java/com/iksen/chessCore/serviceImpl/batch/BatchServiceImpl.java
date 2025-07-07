package com.iksen.chessCore.serviceImpl.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
