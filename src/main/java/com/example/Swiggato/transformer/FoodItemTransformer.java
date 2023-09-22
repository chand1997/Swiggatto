package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.model.FoodItem;

public class FoodItemTransformer {

    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){

            return FoodResponse.builder()
                            .foodCategory(foodItem.getFoodCategory())
                            .price(foodItem.getPrice())
                            .dishName(foodItem.getDishName())
                    .build();




    }
}
