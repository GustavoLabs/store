package com.example.springproject.exception;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException (String message){
        super(message);
    }
}
