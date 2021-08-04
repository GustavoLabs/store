package com.example.springproject.service;

import com.example.springproject.controller.CartController;
import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import com.example.springproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    CartController cartController;

    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        Cart cart = new Cart();
        cartController.createCart(cart);
        user.setCart(cart);
        userRepository.save(user);
        return user;
    }
}
