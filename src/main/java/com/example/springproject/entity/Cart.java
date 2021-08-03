package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name="cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    @OneToOne(mappedBy = "cart")
    private User user;

    public Cart() {
    }

    public List<Product> getProducts(){
        if (products == null){
            products = new ArrayList<>();
        }
        return products;
    }
}
