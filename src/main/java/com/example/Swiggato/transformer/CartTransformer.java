package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.model.Cart;

public class CartTransformer {


    public static CartResponse CartToCartResponse(Cart cart){
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .food(FoodItemTransformer.FoodItemListToFoodResponseList(cart))
                .build();
    }
}
