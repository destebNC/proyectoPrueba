package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.ProductRepository;
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

    public ProductDto productToProductDTO(Product product){

        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getWeight()
        );
    }

    public Product save(Product product){
        return repository.save(product);
    }

    public List<ProductDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::productToDto)
                .toList();
    }

    public ProductDto getById(Integer id){
        Product product = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        return mapper.productToDto(product);
    }

    public void delete(Integer id){
        Product product = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        repository.deleteById(id);
    }

    public ProductDto update(Integer id, ProductDto dto) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setWeight(dto.weight());

        Product updated = repository.save(product);

        return mapper.productToDto(updated);


    }
}
