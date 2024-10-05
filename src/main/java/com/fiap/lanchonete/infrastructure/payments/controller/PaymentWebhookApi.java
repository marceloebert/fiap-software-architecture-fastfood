package com.fiap.lanchonete.infrastructure.payments.controller;

import com.fiap.lanchonete.application.payments.usecases.ApprovePaymentUseCase;
import com.fiap.lanchonete.entities.payments.Payment;
import com.fiap.lanchonete.infrastructure.payments.dto.PaymentApprovalRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentWebhookApi {

    private final ApprovePaymentUseCase approvePaymentUseCase;

    public PaymentWebhookApi(ApprovePaymentUseCase approvePaymentUseCase) {
        this.approvePaymentUseCase = approvePaymentUseCase;
    }

    @PostMapping("/approve")
    public ResponseEntity<Payment> approvePayment(@RequestBody PaymentApprovalRequestDTO paymentApprovalRequestDTO) {
        Payment payment = approvePaymentUseCase.approvePayment(
                paymentApprovalRequestDTO.getPaymentId(),
                paymentApprovalRequestDTO.getTransactionId());
        return ResponseEntity.ok(payment);
    }
}
