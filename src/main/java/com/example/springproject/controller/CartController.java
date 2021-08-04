package com.example.springproject.controller;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import com.example.springproject.repositories.CartRepository;
import com.example.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public Cart createCart(Cart cart){
        return cartRepository.save(cart);
    }

    @GetMapping("/{id}")
    public String getUserByIdCart(@PathVariable Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.get().getUser().getName();
    }
}
