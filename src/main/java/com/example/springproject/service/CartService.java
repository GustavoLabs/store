package com.example.springproject.service;

import com.example.springproject.entity.Cart;
import com.example.springproject.entity.User;
import com.example.springproject.exception.CartNotFoundException;
import com.example.springproject.exception.UserNotFoundException;
import com.example.springproject.model.response.UserResponseDTO;
import com.example.springproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    public Cart findCartById(@Min(1) Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isPresent()){
            throw new CartNotFoundException(String.format("Cart %s not found", id));
        }
        return cart.orElse(null);
    }

    public Cart createNewCart(@NotBlank String userLogin){
        return new Cart(userLogin);
    }


    public UserResponseDTO getUserByCartId(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        User user = cart.get().getUser();
        if (user == null){
            throw new UserNotFoundException(String.format("User %s not found", id));
        }
        return new UserResponseDTO(user);
    }

    public String getUserLogin(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        return cart.map
                (Cart::getUserLogin)
                .orElse(null);
    }
}
