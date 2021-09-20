package com.example.springproject.controller;

import com.example.springproject.entity.Cart;
import com.example.springproject.model.response.ProductQuantityDTO;
import com.example.springproject.model.response.UserResponseDTO;
import com.example.springproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/{id}")
    public Cart findCartByIDd(@PathVariable Long id){
        return cartService.findCartById(id);
    }

    @GetMapping("/user/{id}")
    public UserResponseDTO findUserByIdCart(@PathVariable Long id){
        return cartService.getUserByCartId(id);
    }

    @GetMapping("/products")
    public List<ProductQuantityDTO> getCartProducts(@RequestParam Long idCart){
        return cartService.getCartProducts(idCart);
    }
}
