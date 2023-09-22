package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
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

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest) {
        return FoodItem.builder()
                .foodCategory(foodRequest.getFoodCategory())
                .price(foodRequest.getPrice())
                .veg(foodRequest.isVeg())
                .dishName(foodRequest.getDishName())
                .available(true)
                .build();
    }

    public static CostliestOrCheapestFoodResponse FoodItemToCostliestOrCheapestFoodResponse(FoodItem foodItem){
        return CostliestOrCheapestFoodResponse.builder()
                .restaurantName(foodItem.getRestaurant().getName())
                .dishName(foodItem.getDishName())
                .build();
    }
}
