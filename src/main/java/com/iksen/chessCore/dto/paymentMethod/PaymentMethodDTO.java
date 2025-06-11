package com.iksen.chessCore.dto.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {
    private Integer id;
    private String name;
    private String shortName;
    private String avatar;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // List of country IDs associated via payment_country_maps
    private List<Integer> countryIds;

    
   
}