package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.exception.RestaurantNoFoundException;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.service.FoodItemService;
import com.example.Swiggato.transformer.FoodItemTransformer;
import com.example.Swiggato.utils.ValidationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    final FoodItemRepository foodItemRepository;

    final ValidationUtils validationUtils;

    public FoodItemServiceImpl(FoodItemRepository foodItemRepository, ValidationUtils validationUtils) {
        this.foodItemRepository = foodItemRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public List<FoodResponse> getFoodsOfCategory(FoodCategory foodCategory) {
                 return foodItemRepository.findByFoodCategory(foodCategory)
                         .stream()
                         .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                         .collect(Collectors.toList());
    }

    @Override
    public List<FoodResponse> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory,
                                                                          double price, int restaurantId) {

       return foodItemRepository.getFoodsOfParticularCategoryPriceRestaurant(foodCategory,price,restaurantId)
               .stream()
               .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());


    }

    @Override
    public List<FoodResponse> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId) {
        if(!validationUtils.validateRestaurant(restaurantId))
            throw new RestaurantNoFoundException("Restaurant doesn't exist");


       return foodItemRepository.getAllVegOrNonVegFoodsFromParticularRestaurant(veg,restaurantId)
               .stream()
               .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());

    }

    @Override
    public List<FoodResponse> getFiveCheapestOrCostliestFoodsFromParticularRestaurant(boolean cheapest, int restaurantId) {
        if(!validationUtils.validateRestaurant(restaurantId))
            throw new RestaurantNoFoundException("Restaurant doesn't exist");

       if(cheapest) return foodItemRepository.getFiveCheapestFoodsFromParticularRestaurant(restaurantId)
               .stream()
               .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
               .collect(Collectors.toList());

        return foodItemRepository.getFiveCostliestFoodsFromParticularRestaurant(restaurantId)
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
    }

    @Override
    public List<CostliestOrCheapestFoodResponse> getFiveCheapestOrCostliestFoodsFromParticularCategory(boolean cheapest, FoodCategory foodCategory){
       if(cheapest) return foodItemRepository.getFiveCheapestFoodsFromParticularCategory(foodCategory)
               .stream()
               .map(foodItem -> FoodItemTransformer.FoodItemToCostliestOrCheapestFoodResponse(foodItem))
               .collect(Collectors.toList());

        return foodItemRepository.getFiveCostliestFoodsFromParticularCategory(foodCategory)
                .stream()
                .map(foodItem->FoodItemTransformer.FoodItemToCostliestOrCheapestFoodResponse(foodItem))
                .collect(Collectors.toList());
    }
}
