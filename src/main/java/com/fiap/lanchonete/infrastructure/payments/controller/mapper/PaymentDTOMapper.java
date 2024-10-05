package com.fiap.lanchonete.infrastructure.payments.controller.mapper;

import com.fiap.lanchonete.entities.payments.Payment;
import com.fiap.lanchonete.infrastructure.payments.dto.CreatePaymentRequestDTO;

public class PaymentDTOMapper {

    public static Payment toDomain(CreatePaymentRequestDTO dto) {
        return new Payment(dto.getOrderId(), dto.getAmount(),null,null,null,null);
    }
}
