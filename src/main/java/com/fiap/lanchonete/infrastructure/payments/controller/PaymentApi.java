package com.fiap.lanchonete.infrastructure.payments.controller;

import com.fiap.lanchonete.application.payments.usecases.CreatePaymentUseCase;
import com.fiap.lanchonete.entities.payments.Payment;
import com.fiap.lanchonete.infrastructure.payments.dto.CreatePaymentRequestDTO;
import com.fiap.lanchonete.infrastructure.payments.controller.mapper.PaymentDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentApi {

    private final CreatePaymentUseCase createPaymentUseCase;

    public PaymentApi(CreatePaymentUseCase createPaymentUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(@RequestBody CreatePaymentRequestDTO createPaymentRequestDTO) {
        Payment payment = createPaymentUseCase.createPayment(
                PaymentDTOMapper.toDomain(createPaymentRequestDTO));
        return ResponseEntity.ok(payment);
    }
}
