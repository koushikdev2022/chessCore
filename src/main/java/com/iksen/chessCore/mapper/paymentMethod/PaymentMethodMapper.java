package com.iksen.chessCore.mapper.paymentMethod;

import java.util.List;
import java.util.stream.Collectors;

import com.iksen.chessCore.dto.paymentMethod.PaymentMethodDTO;
import com.iksen.chessCore.model.PaymentMethod;

public class PaymentMethodMapper {

    public static PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return new PaymentMethodDTO(
                paymentMethod.getId(),
                paymentMethod.getName(),
                paymentMethod.getShortName(),
                paymentMethod.getStatus(),
                paymentMethod.getCreatedAt(),
                paymentMethod.getUpdatedAt(),
                paymentMethod.getPaymentCountryMaps()
                        .stream()
                        .map(map -> map.getCountry().getId())
                        .toList()
        );
    }

    public static List<PaymentMethodDTO> toDTOList(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(PaymentMethodMapper::toDTO)
                .collect(Collectors.toList());
    }
}
