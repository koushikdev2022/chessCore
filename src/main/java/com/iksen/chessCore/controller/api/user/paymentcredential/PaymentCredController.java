package com.iksen.chessCore.controller.api.user.paymentcredential;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iksen.chessCore.dto.paymentMethod.PaymentMethodKeyDTO;
import com.iksen.chessCore.serviceImpl.payment.PaymentMethodKeyServiceImpl;

@RestController
@RequestMapping("/api/payment-credential")
public class PaymentCredController {
        @Autowired
        public PaymentMethodKeyServiceImpl paymentMethodKeyServiceImpl;

        @GetMapping("/list/{id}")
        private ResponseEntity<?> paymentCredential(@PathVariable("id") int id  ){
             try{
                    PaymentMethodKeyDTO paykey = paymentMethodKeyServiceImpl.paymentKey(id);
                    return ResponseEntity.status(200).body(Map.of(
                            "status", true,
                            "paykey",paykey,
                            "status_code", 200
                    ));
             }catch (Exception e) {
                    return ResponseEntity.status(400).body(Map.of(
                            "status", false,
                                "message", "something went wrong",
                                "status_code", 400,
                                "error",e
                             ));
                    }
            }
}
