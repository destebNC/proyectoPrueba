package com.example.proyectoPrueba.service;

import com.example.proyectoPrueba.dto.ProductDto;
import com.example.proyectoPrueba.model.Product;
import com.example.proyectoPrueba.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Inyectamos el repositorio que creamos en el paso anterior
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los productos de la base de datos y los convierte a DTO.
     */
    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    /**
     * Busca un producto por ID. Si no lo encuentra, lanza una excepción
     * para que el GlobalExceptionHandler la capture.
     */
    public ProductDto getById(Integer id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .orElseThrow(() -> new RuntimeException("El producto con ID " + id + " no existe en la base de datos"));
    }

    /**
     * Guarda el producto físicamente en la base de datos MySQL.
     */
    public void save(Product product) {
        productRepository.save(product);
    }

    /**
     * Elimina el producto de la base de datos por su ID.
     */
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}