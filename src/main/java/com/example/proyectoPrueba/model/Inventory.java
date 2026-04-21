package com.example.proyectoPrueba.model;

import java.util.List;


public class Inventory {
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> productList;
}
