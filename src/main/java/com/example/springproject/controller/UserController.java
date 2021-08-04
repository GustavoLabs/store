package com.example.springproject.controller;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import com.example.springproject.repositories.CartRepository;
import com.example.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public User createUser(User user) {
        return userService.addUser(user);
    }

}
