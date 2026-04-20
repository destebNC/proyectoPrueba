package com.example.proyectoPrueba.controller;

import com.example.proyectoPrueba.dto.InventarioDTO;
import com.example.proyectoPrueba.dto.ProductoDto;
import com.example.proyectoPrueba.model.Inventario;
import com.example.proyectoPrueba.model.Producto;
import com.example.proyectoPrueba.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventarioController {

    private final ProductService productService;

    public InventarioController (ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/productos")
    public List<ProductoDto> getAll(){
        return productService.getAll();
    }

    //GET BY ID
    @GetMapping("/productos/{product_id}")
    public ProductoDto getById(){
        return productService.getById();
    }

    @PostMapping("/agregar")
    public Producto post(
            @RequestBody Producto producto
            ){
            return productService.save(producto);
    }

}
