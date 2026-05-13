package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Aquí Spring Data JPA hará toda la magia de los SELECT, INSERT y DELETE solo con esto
}