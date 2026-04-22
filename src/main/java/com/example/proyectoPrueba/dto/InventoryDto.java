package com.example.proyectoPrueba.dto;

import com.example.proyectoPrueba.model.Product;
import java.util.List;

public class InventoryDto {
    private Integer id;
    private String name;
    private List<Product> products;

    public InventoryDto() {}

    // --- GETTERS Y SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}