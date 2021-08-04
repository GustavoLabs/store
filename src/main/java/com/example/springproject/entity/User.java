package com.example.springproject.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id", unique = true, nullable = false)
    private Cart cart;

    public User() {
    }

    public User(String name, String email, String login, String password, Cart cart) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.cart = cart;
    }

    public User(String name, String email, String login, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }
}
