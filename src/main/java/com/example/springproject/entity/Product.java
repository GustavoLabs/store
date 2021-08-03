package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @Min(0)
    private double price;

    @Min(0)
    @Max(1)
    private double discount;

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();


    public Product() {
    }

    public Product(String name, String description, Double price, Double discount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
    }
}