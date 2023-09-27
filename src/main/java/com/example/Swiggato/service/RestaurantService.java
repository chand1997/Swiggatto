package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

     RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addMenuItemToRestaurant(MenuRequest addFoodToMenuRequest);

    List<MenuResponse> getMenuOfRestaurant(int id);
}
