package com.fiap.lanchonete.infrastructure.customers.controller.mapper;

import com.fiap.lanchonete.entities.customers.Customer;
import com.fiap.lanchonete.infrastructure.customers.controller.dto.CustomerRequest;
import com.fiap.lanchonete.infrastructure.customers.controller.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapper {

    public Customer toCustomer(CustomerRequest customerRequest) {
        return new Customer(
                null,  // O ID pode ser gerado posteriormente no Use Case ou no banco de dados
                customerRequest.getName(),
                customerRequest.getDocument(),
                customerRequest.getMail()
        );
    }

    public CustomerResponse toResponse(Customer customer) {
        if(customer == null) return null;
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getDocument(), customer.getMail());
    }
}
