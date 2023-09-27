package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.model.Cart;

import java.util.List;
import java.util.stream.Collectors;

public class CartTransformer {


    public static CartResponse CartToCartResponse(Cart cart){

        List<MenuResponse> menuResponseList =cart.getFoodItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem.getMenuItem()))
                .collect(Collectors.toList());

        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .food(menuResponseList)
                .build();
    }
}
