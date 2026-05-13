package com.example.proyectoPrueba.model;

<<<<<<< HEAD
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
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.Map;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private float price;
    private float weight;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @JsonIgnore
    private Inventory inventory;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> attributes;
>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

<<<<<<< HEAD
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private Double price;

    @PositiveOrZero(message = "El peso no puede ser negativo")
    private Double weight;
=======
    public Product(String name, float price, float weight){
        this.name=name;
        this.price=price;
        this.weight=weight;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public float getWeight() { return weight; }
    public void setWeight(float weight) { this.weight = weight; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }
>>>>>>> 650bc9126e1e31b017924211b09a6f701ac34e53
}