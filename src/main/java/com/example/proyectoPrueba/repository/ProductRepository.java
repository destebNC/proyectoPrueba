package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Solo con heredar de JpaRepository ya puedes usar:
    // .save(), .findAll(), .findById(), .deleteById()
}