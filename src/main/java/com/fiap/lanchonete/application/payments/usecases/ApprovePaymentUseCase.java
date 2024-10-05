package com.fiap.lanchonete.application.payments.usecases;

import com.fiap.lanchonete.application.orders.usecases.UpdateOrderStateUseCase;
import com.fiap.lanchonete.entities.payments.Payment;
import com.fiap.lanchonete.application.payments.gateways.PaymentGateway;

import java.util.Optional;
import java.util.UUID;

public class ApprovePaymentUseCase {

    private final PaymentGateway paymentGateway;
    private final UpdateOrderStateUseCase updateOrderStateUseCase;

    public ApprovePaymentUseCase(PaymentGateway paymentGateway,UpdateOrderStateUseCase updateOrderStateUseCase) {
        this.paymentGateway = paymentGateway;
        this.updateOrderStateUseCase = updateOrderStateUseCase;
    }

    public Payment approvePayment(UUID paymentId, String transactionId) {
        Optional<Payment> optionalPayment = paymentGateway.findById(paymentId);

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            if (payment.isPending()) {
                payment.approve(transactionId);
                updateOrderStateUseCase.updateOrderState(payment.getOrderId(), "RECEIVED");
                return paymentGateway.save(payment);
            } else {
                throw new IllegalStateException("Payment is not pending");
            }
        } else {
            throw new IllegalArgumentException("Payment not found");
        }
    }
}
