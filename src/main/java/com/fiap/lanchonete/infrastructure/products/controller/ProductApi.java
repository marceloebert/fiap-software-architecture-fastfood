package com.fiap.lanchonete.infrastructure.products.controller;

import com.fiap.lanchonete.application.products.usecases.*;
import com.fiap.lanchonete.entities.products.Product;
import com.fiap.lanchonete.infrastructure.products.controller.dto.ProductRequest;
import com.fiap.lanchonete.infrastructure.products.controller.mapper.ProductDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductApi {

    private final CreateProductUseCase createProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetProductsByCategoryUseCase getProductsByCategoryUseCase;
    private final RemoveProductUseCase removeProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final ProductDTOMapper productDTOMapper;

    @Autowired
    public ProductApi(CreateProductUseCase createProductUseCase,
                      GetAllProductsUseCase getAllProductsUseCase,
                      GetProductByIdUseCase getProductByIdUseCase,
                      GetProductsByCategoryUseCase getProductsByCategoryUseCase,
                      RemoveProductUseCase removeProductUseCase,
                      UpdateProductUseCase updateProductUseCase,
                      ProductDTOMapper productDTOMapper) {
        this.createProductUseCase = createProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.getProductsByCategoryUseCase = getProductsByCategoryUseCase;
        this.removeProductUseCase = removeProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.productDTOMapper = productDTOMapper;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
        Product product = productDTOMapper.toProductForCreation(productRequest);
        Product savedProduct = createProductUseCase.createProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = getAllProductsUseCase.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Optional<Product> product = getProductByIdUseCase.getById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = getProductsByCategoryUseCase.getByCategory(category);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProduct(@PathVariable UUID id) {
        removeProductUseCase.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductRequest productRequest) {
        Product product = productDTOMapper.toProductForUpdate(productRequest,id);
        product = updateProductUseCase.update(product);
        return ResponseEntity.ok(product);
    }
}
