package com.example.Swiggato.exception;

public class FoodItemNotFoundException extends  RuntimeException {
    public FoodItemNotFoundException(String m){
        super(m);
    }
}
