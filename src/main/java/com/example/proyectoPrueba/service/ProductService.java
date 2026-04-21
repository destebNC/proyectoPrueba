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

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProductDto getById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return convertToDto(product);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void update(Integer id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setWeight(productDto.getWeight());
        productRepository.save(product);
    }

    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setWeight(product.getWeight());
        return dto;
    }
}