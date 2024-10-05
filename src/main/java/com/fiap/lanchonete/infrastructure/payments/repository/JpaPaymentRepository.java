package com.fiap.lanchonete.infrastructure.payments.repository;

import com.fiap.lanchonete.infrastructure.payments.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, UUID> {
}
