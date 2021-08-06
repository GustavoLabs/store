package com.example.springproject.controller;

import com.example.springproject.entity.CartProducts;
import com.example.springproject.service.CartProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartproducts")
public class CartProductsController {

    @Autowired
    CartProductsService cartProductsService;

    @PostMapping
    public CartProducts addProductToCart(Long cartId, Long productId, int quantity){
        return cartProductsService.addProductToCart(cartId, productId, quantity);
    }
}
