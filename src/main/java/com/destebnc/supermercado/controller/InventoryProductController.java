package com.destebnc.supermercado.controller;

import com.destebnc.supermercado.dto.ProductDto;
import com.destebnc.supermercado.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Productos dentro de un inventario. Roles ADMIN y USER (ver SecurityConfig). */
@RestController
@RequestMapping("/api/inventories/{inventoryId}/products")
@Tag(name = "Products")
public class InventoryProductController {

    private final ProductService productService;

    public InventoryProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(operationId = "listInventoryProducts", summary = "List products of an inventory (paginated)")
    public Page<ProductDto> list(@PathVariable Integer inventoryId,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        return productService.listByInventory(inventoryId, page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(operationId = "addProductToInventory", summary = "Add product to an inventory")
    public ProductDto add(@PathVariable Integer inventoryId, @Valid @RequestBody ProductDto productDto) {
        return productService.addToInventory(inventoryId, productDto);
    }

    @PutMapping("/{productId}")
    @Operation(operationId = "updateInventoryProduct", summary = "Update product of an inventory")
    public ProductDto update(@PathVariable Integer inventoryId,
                             @PathVariable Integer productId,
                             @Valid @RequestBody ProductDto productDto) {
        return productService.updateInInventory(inventoryId, productId, productDto);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(operationId = "deleteInventoryProduct", summary = "Delete product of an inventory")
    public void delete(@PathVariable Integer inventoryId, @PathVariable Integer productId) {
        productService.deleteFromInventory(inventoryId, productId);
    }
}
