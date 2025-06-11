package com.iksen.chessCore.serviceImpl.payment;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.iksen.chessCore.dto.paymentMethod.PaymentMethodKeyDTO;
import com.iksen.chessCore.mapper.paymentMethod.PaymentMethodKeyMapper;
import com.iksen.chessCore.model.PaymentMethodKey;
import com.iksen.chessCore.repositary.PaymentMethodKeyRepository;
import com.iksen.chessCore.service.payment.PaymentMethodKeyService;

@Service
public class PaymentMethodKeyServiceImpl implements PaymentMethodKeyService{
        @Autowired
        private PaymentMethodKeyRepository paymentMethodKeyRepository;
        @Autowired
        private PaymentMethodKeyMapper paymentMethodKeyMapper;
        public PaymentMethodKeyDTO paymentKey(int id){
            PaymentMethodKey paym = paymentMethodKeyRepository.findById(id);
            PaymentMethodKeyDTO key =  paymentMethodKeyMapper.toDTO(paym);
            return key;
         }
}
