package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.exception.RestaurantNoFoundException;
import com.example.Swiggato.repository.MenuItemRepository;
import com.example.Swiggato.service.MenuItemService;
import com.example.Swiggato.transformer.MenuItemTransformer;
import com.example.Swiggato.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    final MenuItemRepository menuItemRepository;

    final ValidationUtils validationUtils;

    @Autowired
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository, ValidationUtils validationUtils) {
        this.menuItemRepository = menuItemRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public List<MenuResponse> getFoodsOfCategory(FoodCategory foodCategory) {
                 return menuItemRepository.findByFoodCategory(foodCategory)
                         .stream()
                         .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
                         .collect(Collectors.toList());
    }

    @Override
    public List<MenuResponse> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory,
                                                                          double price, int restaurantId) {

       return menuItemRepository.getFoodsOfParticularCategoryPriceRestaurant(foodCategory,price,restaurantId)
               .stream()
               .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());


    }

    @Override
    public List<MenuResponse> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId) {
        if(!validationUtils.validateRestaurant(restaurantId))
            throw new RestaurantNoFoundException("Restaurant doesn't exist");


       return menuItemRepository.getAllVegOrNonVegFoodsFromParticularRestaurant(veg,restaurantId)
               .stream()
               .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());

    }

    @Override
    public List<MenuResponse> getFiveCheapestOrCostliestFoodsFromParticularRestaurant(boolean cheapest, int restaurantId) {
        if(!validationUtils.validateRestaurant(restaurantId))
            throw new RestaurantNoFoundException("Restaurant doesn't exist");

       if(cheapest) return menuItemRepository.getFiveCheapestFoodsFromParticularRestaurant(restaurantId)
               .stream()
               .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());

        return menuItemRepository.getFiveCostliestFoodsFromParticularRestaurant(restaurantId)
                .stream()
                .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
    }

    @Override
    public List<CostliestOrCheapestFoodResponse> getFiveCheapestOrCostliestFoodsFromParticularCategory(boolean cheapest, FoodCategory foodCategory){
       if(cheapest) return menuItemRepository.getFiveCheapestFoodsFromParticularCategory(foodCategory)
               .stream()
               .map(foodItem -> MenuItemTransformer.FoodItemToCostliestOrCheapestFoodResponse(foodItem))
               .collect(Collectors.toList());

        return menuItemRepository.getFiveCostliestFoodsFromParticularCategory(foodCategory)
                .stream()
                .map(foodItem-> MenuItemTransformer.FoodItemToCostliestOrCheapestFoodResponse(foodItem))
                .collect(Collectors.toList());
    }
}
