package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByInventoryId(Integer inventoryId, Pageable pageable);

}