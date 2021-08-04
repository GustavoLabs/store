package com.example.springproject.exception;

public class UserNotFoundException extends  RuntimeException{

    public UserNotFoundException (String message){
        super(message);
    }
}
