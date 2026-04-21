package com.example.proyectoPrueba.dto;

import com.example.proyectoPrueba.model.Product;
import java.util.List;

public class InventoryDto {
    private Integer id;
    private String name;
    private List<Product> productList; // Añadido para que coincida con tu código

    public InventoryDto() {
    }

    public InventoryDto(String name, List<Product> productList) {
        this.name = name;
        this.productList = productList;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
}