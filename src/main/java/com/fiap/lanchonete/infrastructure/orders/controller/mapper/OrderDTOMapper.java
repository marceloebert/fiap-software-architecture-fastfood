package com.fiap.lanchonete.infrastructure.orders.controller.mapper;

import com.fiap.lanchonete.entities.orders.Order;
import com.fiap.lanchonete.entities.orders.OrderItem;
import com.fiap.lanchonete.entities.products.Product;
import com.fiap.lanchonete.infrastructure.orders.controller.dto.OrderItemRequest;
import com.fiap.lanchonete.infrastructure.orders.controller.dto.OrderRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDTOMapper {

    public Order toOrder(OrderRequest orderRequest) {
        List<OrderItem> items = orderRequest.getItems().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toList());

        return new Order(orderRequest.getCustomer(), items);
    }

    public OrderItem toOrderItem(OrderItemRequest itemRequest) {
        return new OrderItem(
                itemRequest.getProductId(),
                itemRequest.getQuantity(),
                itemRequest.getObservation()
        );
    }
}
