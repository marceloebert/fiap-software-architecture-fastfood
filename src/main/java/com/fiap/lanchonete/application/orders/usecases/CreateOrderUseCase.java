package com.fiap.lanchonete.application.orders.usecases;

import com.fiap.lanchonete.application.orders.gateways.OrderGateway;
import com.fiap.lanchonete.application.products.gateways.ProductGateway;
import com.fiap.lanchonete.application.customers.gateways.CustomerGateway;
import com.fiap.lanchonete.entities.orders.Order;
import com.fiap.lanchonete.entities.orders.OrderItem;
import com.fiap.lanchonete.entities.products.Product;
import com.fiap.lanchonete.entities.customers.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderUseCase {

    private final OrderGateway orderGateway;
    private final ProductGateway productGateway;
    private final CustomerGateway customerGateway;

    @Autowired
    public CreateOrderUseCase(OrderGateway orderGateway, ProductGateway productGateway, CustomerGateway customerGateway) {
        this.orderGateway = orderGateway;
        this.productGateway = productGateway;
        this.customerGateway = customerGateway;
    }

    public Order createOrder(Order order, String customerDocument) {

        Customer customer;
        if (customerDocument == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        else {

            customer = customerGateway.findCustomer(customerDocument)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        }

        List<OrderItem> validatedItems = order.getItems().stream()
                .map(item -> {
                    Product product = productGateway.getById(item.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                    return new OrderItem(
                            UUID.randomUUID(),
                            product,
                            item.getQuantity(),
                            item.getObservation()
                    );
                })
                .collect(Collectors.toList());
        Order validatedOrder = new Order(customer, validatedItems);

        return orderGateway.save(validatedOrder);
    }
}
