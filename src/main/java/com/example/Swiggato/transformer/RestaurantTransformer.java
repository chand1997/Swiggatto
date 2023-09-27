package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantTransformer {


    public static Restaurant RestaurantRequestToRestaurant(RestaurantRequest restaurantRequest){
        return Restaurant.builder()
                .name(restaurantRequest.getName())
                .location(restaurantRequest.getLocation())
                .restaurantCategory(restaurantRequest.getRestaurantCategory())
                .contactNo(restaurantRequest.getContactNo())
                .availableMenuItems(new ArrayList<>())
                .orders(new ArrayList<>())
                .opened(true)
                .build();
    }

    public static RestaurantResponse RestaurantToRestaurantResponse(Restaurant restaurant){
        List<MenuResponse> menu=restaurant.getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return RestaurantResponse.builder()
                .menu(menu)
                .opened(restaurant.isOpened())
                .contactNo(restaurant.getContactNo())
                .name(restaurant.getName())
                .location(restaurant.getLocation())
                .build();
    }
}
