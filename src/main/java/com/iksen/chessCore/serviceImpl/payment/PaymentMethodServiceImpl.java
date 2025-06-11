package com.iksen.chessCore.serviceImpl.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iksen.chessCore.dto.paymentMethod.PaymentMethodDTO;
import com.iksen.chessCore.mapper.paymentMethod.PaymentMethodMapper;

import com.iksen.chessCore.model.PaymentMethod;
import com.iksen.chessCore.repositary.PaymentMethodRepo;
import com.iksen.chessCore.service.payment.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
            @Autowired 
            private PaymentMethodRepo paymentMethodRepo;
            @Autowired
            private PaymentMethodMapper paymentMethodMapper;
            @Override
            public List<PaymentMethodDTO> payment(int id){
                   List<PaymentMethod> paymentEntity= paymentMethodRepo.findByCountryId(id);
                   List <PaymentMethodDTO> paymentMethod =  paymentMethodMapper.toDTOList(paymentEntity);
                   return paymentMethod;
            }

}
