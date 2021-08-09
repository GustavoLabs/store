package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.CartProducts;
import com.example.springproject.entity.CartProductsKey;
import com.example.springproject.entity.Product;
import com.example.springproject.repositories.CartProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartProductsService {

    @Autowired
    private CartProductsRepository cartProductsRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    public CartProducts addProductToCart(Long cartId, Long productId, int quantity){
        Cart cart = cartService.findCartById(cartId);
        Product product = productService.findProductById(productId);
        CartProducts cartProducts = new CartProducts(new CartProductsKey(cart.getId(), product.getId()), quantity, cart, product);
        cartProductsRepository.save(cartProducts);
        return  cartProducts;
    }



}
