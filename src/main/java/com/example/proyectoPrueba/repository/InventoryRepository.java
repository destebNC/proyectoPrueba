package com.example.proyectoPrueba.repository;

import com.example.proyectoPrueba.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}
