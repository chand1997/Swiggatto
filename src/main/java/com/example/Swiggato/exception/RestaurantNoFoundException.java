package com.example.Swiggato.exception;

public class RestaurantNoFoundException extends RuntimeException{
    public RestaurantNoFoundException(String message){
        super(message);
    }
}
