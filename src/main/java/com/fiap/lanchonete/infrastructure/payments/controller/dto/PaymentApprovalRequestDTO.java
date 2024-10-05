package com.fiap.lanchonete.infrastructure.payments.dto;

import java.util.UUID;

public class PaymentApprovalRequestDTO {
    private UUID paymentId;
    private String transactionId;

    // Getters e Setters
    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
