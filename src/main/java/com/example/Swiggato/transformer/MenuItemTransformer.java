package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.model.MenuItem;

public class MenuItemTransformer {

    public static MenuResponse FoodItemToFoodResponse(MenuItem menuItem){

            return MenuResponse.builder()
                            .foodCategory(menuItem.getFoodCategory())
                            .price(menuItem.getPrice())
                            .dishName(menuItem.getDishName())
                    .build();

    }

    public static MenuItem FoodRequestToFoodItem(MenuRequest menuRequest) {
        return MenuItem.builder()
                .foodCategory(menuRequest.getFoodCategory())
                .price(menuRequest.getPrice())
                .veg(menuRequest.isVeg())
                .dishName(menuRequest.getDishName())
                .available(true)
                .build();
    }

    public static CostliestOrCheapestFoodResponse FoodItemToCostliestOrCheapestFoodResponse(MenuItem menuItem){
        return CostliestOrCheapestFoodResponse.builder()
                .restaurantName(menuItem.getRestaurant().getName())
                .dishName(menuItem.getDishName())
                .build();
    }
}
