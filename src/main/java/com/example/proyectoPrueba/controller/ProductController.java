package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Products") // Alineado con tu openapi.yaml manual
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(operationId = "apiProductosGet", summary = "List all products")
    public ResponseEntity<List<ProductDto>> getAll() {
        // En tu service el método se llama getAll()
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    @Operation(operationId = "apiProductosPost", summary = "Add standalone product")
    public ResponseEntity<Product> addStandaloneProduct(@RequestBody Product product) {
        // En tu service el método se llama save() y es void
        productService.save(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/paginated")
    @Operation(operationId = "apiProductosPaginatedGet", summary = "List all products (Paginated)")
    public ResponseEntity<Page<ProductDto>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // En tu service se llama getAllPaginated()
        return ResponseEntity.ok(productService.getAllPaginated(page, size));
    }

    @GetMapping("/{product_id}")
    @Operation(operationId = "apiProductosProductIdGet", summary = "Get product by ID")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer product_id) {
        // En tu service se llama getById()
        return ResponseEntity.ok(productService.getById(product_id));
    }

    @PostMapping("/inv/{inventory_id}")
    @Operation(operationId = "apiProductosInvInventoryIdPost", summary = "Add product to specific inventory")
    public ResponseEntity<Product> addProductToInventory(
            @PathVariable Integer inventory_id,
            @RequestBody Product product) {
        // En tu service se llama addProduct()
        productService.addProduct(inventory_id, product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/inv/{inventory_id}/paginated")
    @Operation(operationId = "apiProductosInvInventoryIdPaginatedGet", summary = "List products from inventory (Paginated)")
    public ResponseEntity<Page<ProductDto>> getProductsByInventoryPaginated(
            @PathVariable Integer inventory_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Este nombre coincide con tu service
        return ResponseEntity.ok(productService.getProductsByInventoryPaginated(inventory_id, page, size));
    }

    @PutMapping("/inv/{inventory_id}/{product_id}")
    @Operation(operationId = "apiProductosInvInventoryIdProductIdPut", summary = "Update product in inventory")
    public ResponseEntity<Product> update(
            @PathVariable Integer inventory_id,
            @PathVariable Integer product_id,
            @RequestBody Product product) {
        // En tu service se llama updateProduct()
        productService.updateProduct(inventory_id, product_id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/inv/{inventory_id}/{product_id}")
    @Operation(operationId = "apiProductosInvInventoryIdProductIdDelete", summary = "Delete product from inventory")
    public ResponseEntity<Void> delete(
            @PathVariable Integer inventory_id,
            @PathVariable Integer product_id) {
        // En tu service se llama delete()
        productService.delete(inventory_id, product_id);
        return ResponseEntity.noContent().build();
    }
}