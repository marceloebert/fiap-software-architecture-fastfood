package com.fiap.lanchonete.entities.orders;

import com.fiap.lanchonete.entities.customers.Customer;
import com.fiap.lanchonete.entities.orders.enums.OrderState;
import com.fiap.lanchonete.entities.orders.enums.PaymentConfirmationStatus;
import com.fiap.lanchonete.entities.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;
    private Customer customer;
    private List<OrderItem> items;
    private OrderState state;
    private BigDecimal totalPrice;
    private PaymentConfirmationStatus paymentConfirmationStatus;
    private Payment payment;
    private LocalDateTime creationTime;

    // Construtor que aceita apenas Customer e Items e gera um UUID automaticamente
    public Order(Customer customer, List<OrderItem> items) {
        this(UUID.randomUUID(), customer, items);
    }

    // Construtor que aceita UUID, Customer e Items
    public Order(UUID id, Customer customer, List<OrderItem> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.state = OrderState.PENDING;
        this.totalPrice = calculateTotalPrice();
        this.paymentConfirmationStatus = PaymentConfirmationStatus.PENDING;
        this.creationTime = LocalDateTime.now();
    }

    // Construtor completo que inclui todos os campos
    public Order(UUID id, Customer customer, List<OrderItem> items, OrderState state,
                 BigDecimal totalPrice, PaymentConfirmationStatus paymentConfirmationStatus,
                 Payment payment, LocalDateTime creationTime) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.state = state;
        this.totalPrice = totalPrice != null ? totalPrice : calculateTotalPrice();
        this.paymentConfirmationStatus = paymentConfirmationStatus;
        this.payment = payment;
        this.creationTime = creationTime;
    }

    // Método para calcular o total da ordem
    private BigDecimal calculateTotalPrice() {
        return items.stream()
                .filter(item -> item != null && item.getProduct() != null && item.getTotalPrice() != null) // Filtro para evitar nulos
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    // Getters e Setters

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public PaymentConfirmationStatus getPaymentConfirmationStatus() {
        return paymentConfirmationStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    // Métodos para processar o pagamento
    public void approvePayment(Payment payment) {
        this.payment = payment;
        this.paymentConfirmationStatus = PaymentConfirmationStatus.APPROVED;
        this.state = OrderState.RECEIVED;
    }

    public void refusePayment() {
        this.payment = null;
        this.paymentConfirmationStatus = PaymentConfirmationStatus.REFUSED;
        this.state = OrderState.PENDING;
    }

    // Método para processar o pagamento baseado no status
    public void processPayment(Payment payment) {
        if (payment != null) {
            if (payment.isApproved()) {
                approvePayment(payment);
            } else {
                refusePayment();
            }
        } else {
            this.paymentConfirmationStatus = PaymentConfirmationStatus.PENDING;
        }
    }

    // Método para atualizar o estado da ordem
    public void updateState(OrderState newState) {
            this.state = newState;
    }
}