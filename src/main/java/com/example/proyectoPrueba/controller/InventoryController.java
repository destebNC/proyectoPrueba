package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    private final ProductService productService;

    public InventoryController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/products")
    public List<ProductDto> getAll(){
        return productService.getAll();
    }

    //GET BY ID
    @GetMapping("/products/{product_id}")
    public ProductDto getById(
            @PathVariable("product_id") Integer id
    ){
        return productService.getById(id);
    }

    @PostMapping("/add")
    public String post(
            @RequestBody Product product
            ){
            productService.save(product);
            return "Producto guardado con éxito";
    }

    @DeleteMapping("/delete/{product_id}")
    public String delete(
            @PathVariable("product_id") Integer id
    ){
        productService.delete(id);
        return "El producto con id: "+id+" ha sido eliminado con éxito";
    }

    @PutMapping("/update/{product_id}")
    public String put(
            @PathVariable Integer id,
            @RequestBody ProductDto productDto
    ){
        productService.update(id, productDto);
        return "El producto con id: "+id+" ha sido modificado";
    }

}
