package com.iksen.chessCore.service.payment;

import java.util.List;

import com.iksen.chessCore.dto.paymentMethod.PaymentMethodDTO;

public interface PaymentMethodService {
     List<PaymentMethodDTO> payment(int id);
}
