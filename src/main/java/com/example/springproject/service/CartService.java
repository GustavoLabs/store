package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.CartProducts;
import com.example.springproject.entity.Product;
import com.example.springproject.entity.User;
import com.example.springproject.exception.CartNotFoundException;
import com.example.springproject.exception.UserNotFoundException;
import com.example.springproject.model.response.ProductQuantityDTO;
import com.example.springproject.model.response.UserResponseDTO;
import com.example.springproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    public Cart findCartById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isPresent()){
            throw new CartNotFoundException(String.format("Cart %s not found", id));
        }
        return cart.get();
    }

    public Cart createNewCart(){
        return new Cart();
    }


    public UserResponseDTO getUserByCartId(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isPresent()){
            User user = cart.get().getUser();
            return new UserResponseDTO(user);
        } else{
            throw new CartNotFoundException(String.format("Cart %s not found", id));
        }
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public List<ProductQuantityDTO> getCartProducts(Long idCart){
        Cart cart = findCartById(idCart);
        List<ProductQuantityDTO> products = new ArrayList<>();
        for(CartProducts cartProducts : cart.getCartProducts()){
            products.add(new ProductQuantityDTO(cartProducts.getProduct(), cartProducts.getQuantity()));
        }
        return products;
    }

    public void alterQuantity(Long cartId, Long productId, int quantity){
        for (ProductQuantityDTO productQuantityDTO : getCartProducts(cartId)){
            if(productQuantityDTO.getProduct().getId().equals(productId)){
               productQuantityDTO.setQuantity(quantity);
            }
        }
    }

}
