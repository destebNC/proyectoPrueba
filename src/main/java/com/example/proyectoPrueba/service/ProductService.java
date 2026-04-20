package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.InventarioDTO;
import com.example.proyectoPrueba.dto.ProductoDto;
import com.example.proyectoPrueba.model.Inventario;
import com.example.proyectoPrueba.model.Producto;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final Mapper mapper;
    private final ProductRepository repository;

    public ProductService(Mapper mapper, ProductRepository repository) {
        this.mapper = mapper;
        this.repository=repository;
    }

    public ProductoDto productToProductDTO(Producto product){

        return new ProductoDto(
                product.getName(),
                product.getPrice()
        );
    }

    public InventarioDTO inventarioToDTO (Inventario inventario){
        return mapper.toDto(inventario);
    }

    public Producto save(Producto producto){
        return repository.save(producto);
    }

    public List<ProductoDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ProductoDto getById(){
        return repository.findById()
                .stream()
                .map(mapper::toDto)
                .toString();
    }
}
