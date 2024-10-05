package com.fiap.lanchonete.infrastructure.payments.configuration;

import com.fiap.lanchonete.application.orders.usecases.UpdateOrderStateUseCase;
import com.fiap.lanchonete.application.payments.gateways.PaymentGateway;
import com.fiap.lanchonete.application.payments.usecases.ApprovePaymentUseCase;
import com.fiap.lanchonete.application.payments.usecases.CreatePaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    private final PaymentGateway paymentGateway;

    public PaymentConfiguration(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase() {
        return new CreatePaymentUseCase(paymentGateway);
    }

    @Bean
    public ApprovePaymentUseCase approvePaymentUseCase(PaymentGateway paymentGateway, UpdateOrderStateUseCase updateOrderStateUseCase) {
        return new ApprovePaymentUseCase(paymentGateway, updateOrderStateUseCase);
    }
}
