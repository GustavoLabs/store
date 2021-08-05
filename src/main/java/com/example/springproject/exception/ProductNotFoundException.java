package com.example.springproject.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException (String message){
        super(message);
    }
}
