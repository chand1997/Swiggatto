package com.example.Swiggato.service;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.enums.FoodCategory;

import java.util.List;

public interface MenuItemService {
    List<MenuResponse> getFoodsOfCategory(FoodCategory foodCategory);

    List<MenuResponse> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory, double price, int restaurantId);

    List<MenuResponse> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId);

    List<MenuResponse> getFiveCheapestOrCostliestFoodsFromParticularRestaurant(boolean cheapest, int restaurantId);

    List<CostliestOrCheapestFoodResponse> getFiveCheapestOrCostliestFoodsFromParticularCategory(boolean cheapest, FoodCategory foodCategory);
}
