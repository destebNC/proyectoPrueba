package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {
}
