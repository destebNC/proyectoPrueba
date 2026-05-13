package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
<<<<<<< HEAD
    // Aquí Spring Data JPA hará toda la magia de los SELECT, INSERT y DELETE solo con esto
=======

    Page<Product> findByInventoryId(Integer inventoryId, Pageable pageable);

>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
}