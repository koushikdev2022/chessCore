package com.iksen.chessCore.repositary;

import com.iksen.chessCore.model.PaymentMethodKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodKeyRepository extends JpaRepository<PaymentMethodKey, Integer> {
      PaymentMethodKey findById(int id);
}