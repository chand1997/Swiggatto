package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemTransformer {

    public static List<FoodResponse> FoodItemListToFoodResponseList(Cart cart){
        List<FoodResponse> foodResponseList=new ArrayList<>();
        List<FoodItem> foodItemList=cart.getFoodItems();
        for(FoodItem item:foodItemList){
            foodResponseList.add(FoodResponse.builder()
                            .foodCategory(item.getFoodCategory())
                            .price(item.getPrice())
                            .dishName(item.getDishName())
                    .build());
        }
        return foodResponseList;
    }
}
