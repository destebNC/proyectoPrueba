package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    private final ProductService productService;

    public InventoryController(ProductService productService){
        this.productService = productService;
    }

    /**
     * operationId: listarProductos
     * GET /productos
     */
    @GetMapping("/productos")
    public ResponseEntity<List<ProductDto>> listarProductos() {
        List<ProductDto> productos = productService.getAll();
        return ResponseEntity.ok(productos);
    }

    /**
     * operationId: buscarProductoPorId
     * GET /productos/{product_id}
     */
    @GetMapping("/productos/{product_id}")
    public ResponseEntity<ProductDto> buscarProductoPorId(
            @PathVariable("product_id") Integer product_id
    ) {
        ProductDto producto = productService.getById(product_id);
        // El GlobalExceptionHandler se encargará de lanzar un 404 si producto es null
        return ResponseEntity.ok(producto);
    }

    /**
     * operationId: agregarProducto
     * POST /agregar
     */
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProducto(
            @RequestBody Product product
    ) {
        productService.save(product);
        return ResponseEntity.status(HttpStatus.OK).body("Producto guardado con éxito");
    }

    /**
     * operationId: borrarProducto
     * DELETE /delete/{product_id}
     */
    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<String> borrarProducto(
            @PathVariable("product_id") Integer product_id
    ) {
        productService.delete(product_id);
        return ResponseEntity.ok("El producto con id: " + product_id + " ha sido eliminado con éxito");
    }
}