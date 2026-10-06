package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Gestion de productos. Roles ADMIN y USER (ver SecurityConfig). */
@RestController
@RequestMapping("/api/productos")
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(operationId = "apiProductosGet", summary = "List all products")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "apiProductosPost", summary = "Add standalone product")
    public ProductDto create(@Valid @RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @GetMapping("/paginated")
    @Operation(operationId = "apiProductosPaginatedGet", summary = "List all products (Paginated)")
    public Page<ProductDto> getAllPaginated(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        return productService.getAllPaginated(page, size);
    }

    @GetMapping("/{product_id}")
    @Operation(operationId = "apiProductosProductIdGet", summary = "Get product by ID")
    public ProductDto getById(@PathVariable("product_id") Integer productId) {
        return productService.getById(productId);
    }

    @PostMapping("/inv/{inventory_id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "apiProductosInvInventoryIdPost", summary = "Add product to specific inventory")
    public ProductDto addToInventory(@PathVariable("inventory_id") Integer inventoryId,
                                     @Valid @RequestBody ProductDto productDto) {
        return productService.addToInventory(inventoryId, productDto);
    }

    @GetMapping("/inv/{inventory_id}/paginated")
    @Operation(operationId = "apiProductosInvInventoryIdPaginatedGet", summary = "List products from inventory (Paginated)")
    public Page<ProductDto> getByInventoryPaginated(@PathVariable("inventory_id") Integer inventoryId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return productService.getByInventoryPaginated(inventoryId, page, size);
    }

    @PutMapping("/inv/{inventory_id}/{product_id}")
    @Operation(operationId = "apiProductosInvInventoryIdProductIdPut", summary = "Update product in inventory")
    public ProductDto update(@PathVariable("inventory_id") Integer inventoryId,
                             @PathVariable("product_id") Integer productId,
                             @Valid @RequestBody ProductDto productDto) {
        return productService.update(inventoryId, productId, productDto);
    }

    @DeleteMapping("/inv/{inventory_id}/{product_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "apiProductosInvInventoryIdProductIdDelete", summary = "Delete product from inventory")
    public void delete(@PathVariable("inventory_id") Integer inventoryId,
                       @PathVariable("product_id") Integer productId) {
        productService.delete(inventoryId, productId);
    }
}
