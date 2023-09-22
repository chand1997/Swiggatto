package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {


    public static CartResponse CartToCartResponse(Cart cart){

        List<FoodResponse> foodResponseList=cart.getFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .food(foodResponseList)
                .build();
    }
}
