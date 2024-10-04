package com.fiap.lanchonete.infrastructure.payment.entity;

import com.fiap.lanchonete.entities.payment.Payment;
import com.fiap.lanchonete.entities.payment.enums.PaymentStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private BigDecimal amount;

    private LocalDateTime paymentDate;

    private String transactionId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    // Construtor vazio para o JPA
    public PaymentEntity() {}

    // Construtor baseado no domínio
    public PaymentEntity(Payment payment) {
        this.id = payment.id(); // Acessando diretamente os campos do record
        this.amount = payment.amount();
        this.paymentDate = payment.paymentDate();
        this.transactionId = payment.transactionId();
        this.paymentStatus = payment.paymentStatus();
    }

    // Método para converter para o domínio
    public Payment toPayment() {
        return new Payment(id, paymentDate, amount, transactionId, paymentStatus);
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
