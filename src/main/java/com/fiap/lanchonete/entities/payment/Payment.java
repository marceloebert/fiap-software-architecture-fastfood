package com.fiap.lanchonete.entities.payment;

import com.fiap.lanchonete.entities.payment.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Payment(
        UUID id,
        LocalDateTime paymentDate,
        BigDecimal amount,
        String transactionId,
        PaymentStatus paymentStatus
) {
    public boolean isApproved() {
        return this.paymentStatus == PaymentStatus.APPROVED;
    }
}
