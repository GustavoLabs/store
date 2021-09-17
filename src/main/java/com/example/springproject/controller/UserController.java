package com.example.springproject.controller;

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
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO user) {
        return userService.addUser(user);
    }


    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@Min(1) @PathVariable Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
