package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

     RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addFoodItemToRestaurant(FoodRequest addFoodToMenuRequest);

    List<FoodResponse> getMenuOfRestaurant(int id);
}
