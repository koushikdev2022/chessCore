package com.iksen.chessCore.mapper.paymentMethod;



import com.iksen.chessCore.dto.paymentMethod.PaymentMethodKeyDTO;
import com.iksen.chessCore.model.PaymentMethodKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PaymentMethodKeyMapper {

    public PaymentMethodKeyDTO toDTO(PaymentMethodKey entity) {
        return new PaymentMethodKeyDTO(
                entity.getId(),
                entity.getPublicKey(),
                entity.getPrivateKey(),
                entity.getAditionalKey(),
                entity.getPaymentMethod() != null ? entity.getPaymentMethod().getId() : null,
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
    public Optional<PaymentMethodKeyDTO> toDTOOp(Optional<PaymentMethodKey> optionalEntity) {
        return optionalEntity.map(this::toDTO);
    }
    public List<PaymentMethodKeyDTO> toDTOList(List<PaymentMethodKey> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

