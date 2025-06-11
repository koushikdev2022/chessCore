package com.iksen.chessCore.mapper.paymentMethod;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.iksen.chessCore.dto.paymentMethod.PaymentMethodDTO;
import com.iksen.chessCore.model.PaymentMethod;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodMapper {

    @Value("${app.admin-url}")
    private String adminUrl;  // ✅ NOT static

    public PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return new PaymentMethodDTO(
                paymentMethod.getId(),
                paymentMethod.getName(),
                paymentMethod.getShortName(),
                paymentMethod.getAvatar() != null ? adminUrl + paymentMethod.getAvatar() : null,
                paymentMethod.getStatus(),
                paymentMethod.getCreatedAt(),
                paymentMethod.getUpdatedAt(),
                paymentMethod.getPaymentCountryMaps()
                        .stream()
                        .map(map -> map.getCountry().getId())
                        .toList()
        );
    }

    public List<PaymentMethodDTO> toDTOList(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
