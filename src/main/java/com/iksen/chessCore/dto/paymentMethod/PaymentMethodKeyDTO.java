package com.iksen.chessCore.dto.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodKeyDTO {

    private Integer id;
    private String publicKey;
    private String privateKey;
    private String aditionalKey;
    private Integer paymentMethodId;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
