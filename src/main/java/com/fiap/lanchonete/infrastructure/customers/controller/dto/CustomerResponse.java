package com.fiap.lanchonete.infrastructure.customers.controller.dto;


import java.util.UUID;

public record CustomerResponse(UUID id, String name, String document, String mail) {
}
