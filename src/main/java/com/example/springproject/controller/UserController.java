package com.example.springproject.controller;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;
import com.example.springproject.model.request.UserRequestDTO;
import com.example.springproject.model.response.UserResponseDTO;
import com.example.springproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping
    public UserResponseDTO createUser(@Valid UserRequestDTO user) {
        return userService.addUser(user);
    }

    @GetMapping
    public UserResponseDTO getUserById(@Min(1) @RequestParam Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{login}")
    public void deleteUser(@PathVariable String login){
        userService.deleteUserByLogin(login);
    }

}
