package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventarioDTO;
import com.example.proyectoPrueba.dto.ProductoDto;
import com.example.proyectoPrueba.model.Inventario;
import com.example.proyectoPrueba.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductoDto productToProductDTO(Producto product){

        return new ProductoDto(
                product.getName(),
                product.getPrice()
        );
    }

    public InventarioDTO inventarioToInventarioDTO(Inventario inventario){

        //  FALTA ESTO
    }

}
