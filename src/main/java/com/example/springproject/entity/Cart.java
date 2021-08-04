package com.example.springproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String userLogin;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name="cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="product_id", referencedColumnName = "id")
    )
    private List<Product> products;

    @OneToOne(mappedBy = "cart")
    @JsonBackReference
    private User user;

    public Cart() {
    }

    public Cart(String userLogin) {
        this.userLogin = userLogin;
    }

    public List<Product> getProducts(){
        if (products == null){
            products = new ArrayList<>();
        }
        return products;
    }
}
