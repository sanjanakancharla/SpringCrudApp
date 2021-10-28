package com.example.restAPI10.exception;

public class UserAlreadyPresentException extends RuntimeException{

    public UserAlreadyPresentException() {
        super("User Already Found !!");
    }
}
