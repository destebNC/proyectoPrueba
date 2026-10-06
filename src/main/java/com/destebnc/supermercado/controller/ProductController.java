package com.destebnc.supermercado.controller;

import com.destebnc.supermercado.dto.ProductDto;
import com.destebnc.supermercado.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Catalogo completo de productos. Roles ADMIN y USER (ver SecurityConfig). */
@RestController
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(operationId = "listProducts", summary = "List all products (paginated)")
    public Page<ProductDto> list(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return productService.list(page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "createProduct", summary = "Create a product without inventory")
    public ProductDto create(@Valid @RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @GetMapping("/{id}")
    @Operation(operationId = "getProduct", summary = "Get product by ID")
    public ProductDto get(@PathVariable Integer id) {
        return productService.get(id);
    }
}
