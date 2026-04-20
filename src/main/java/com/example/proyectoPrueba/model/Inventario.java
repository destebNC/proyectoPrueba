package com.example.proyectoPrueba.model;

import java.util.List;


public class Inventario {
    public List<Producto> getProductList() {
        return productList;
    }

    public void setProductList(List<Producto> productList) {
        this.productList = productList;
    }

    public List<Producto> productList;
}
