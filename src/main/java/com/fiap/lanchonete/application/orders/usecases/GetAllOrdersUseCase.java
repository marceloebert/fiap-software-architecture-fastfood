package com.fiap.lanchonete.application.orders.usecases;

import com.fiap.lanchonete.application.orders.gateways.OrderGateway;
import com.fiap.lanchonete.entities.orders.Order;

import java.util.List;

public class GetAllOrdersUseCase {

    private final OrderGateway orderGateway;

    public GetAllOrdersUseCase(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    public List<Order> getAll() {
        return orderGateway.findAll();
    }
}

