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
    @JoinColumn(name = "cart_id", unique = true)
    private Cart cart;

}
