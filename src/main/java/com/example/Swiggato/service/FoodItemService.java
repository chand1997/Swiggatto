package com.example.Swiggato.service;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.enums.FoodCategory;

import java.util.List;

public interface FoodItemService {
    List<FoodResponse> getFoodsOfCategory(FoodCategory foodCategory);

    List<FoodResponse> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory, double price, int restaurantId);

    List<FoodResponse> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId);

    List<FoodResponse> getFiveCheapestOrCostliestFoodsFromParticularRestaurant(boolean cheapest, int restaurantId);

    List<CostliestOrCheapestFoodResponse> getFiveCheapestOrCostliestFoodsFromParticularCategory(boolean cheapest, FoodCategory foodCategory);
}
