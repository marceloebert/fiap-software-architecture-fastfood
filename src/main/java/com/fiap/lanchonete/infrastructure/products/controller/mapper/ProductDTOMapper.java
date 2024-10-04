package com.fiap.lanchonete.infrastructure.products.controller.mapper;

import com.fiap.lanchonete.entities.products.Product;
import com.fiap.lanchonete.infrastructure.products.controller.dto.ProductRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductDTOMapper {

    // Método para criar um novo produto com um UUID gerado
    public Product toProductForCreation(ProductRequest productRequest) {
        return new Product(
                UUID.randomUUID(),  // Gera um novo UUID
                productRequest.getDescription(),
                productRequest.getPrice(),
                com.fiap.lanchonete.entities.products.enums.Category.valueOf(productRequest.getCategory())
        );
    }

    // Método para atualização, que usa o ID existente
    public Product toProductForUpdate(ProductRequest productRequest, UUID id) {
        return new Product(
                id,  // Usa o ID existente
                productRequest.getDescription(),
                productRequest.getPrice(),
                com.fiap.lanchonete.entities.products.enums.Category.valueOf(productRequest.getCategory())
        );
    }
}
