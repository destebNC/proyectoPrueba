package com.example.proyectoPrueba.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos") // Nombre de la tabla en MySQL
@Data // Genera Getters, Setters, toString, equals y hashCode automáticamente
@NoArgsConstructor // Constructor vacío obligatorio para JPA
@AllArgsConstructor // Constructor con todos los campos
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento para MySQL
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double price;

    @PositiveOrZero(message = "El peso no puede ser negativo")
    private Double weight;
}