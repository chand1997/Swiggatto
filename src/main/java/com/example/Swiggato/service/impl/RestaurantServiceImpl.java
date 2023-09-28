package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNoFoundException;
import com.example.Swiggato.model.MenuItem;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.RestaurantService;
import com.example.Swiggato.transformer.MenuItemTransformer;
import com.example.Swiggato.transformer.RestaurantTransformer;
import com.example.Swiggato.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    final RestaurantRepository restaurantRepository;

    final ValidationUtils validationUtils;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ValidationUtils validationUtils) {
        this.restaurantRepository = restaurantRepository;
        this.validationUtils = validationUtils;
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest){
        Restaurant restaurant= RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        Restaurant savedRestaurant=restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    @Override
    public String changeOpenedStatus(int id) {

       if(!validationUtils.validateRestaurant(id)) throw new RestaurantNoFoundException("Invalid id");

       Restaurant restaurant=restaurantRepository.findById(id).get();

       restaurant.setOpened(!restaurant.isOpened());

       Restaurant savedRestaurant= restaurantRepository.save(restaurant);

       if(savedRestaurant.isOpened()) return "Restaurant is Opened Now!!";
        return "Restaurant is Closed Now!!";
    }

    @Override
    public RestaurantResponse addMenuItemToRestaurant(MenuRequest menuRequest) {

        if(!validationUtils.validateRestaurant(menuRequest.getRestaurantId()))
            throw new RestaurantNoFoundException("Restaurant doesn't exist!!");

        Restaurant restaurant=restaurantRepository.findById(menuRequest.getRestaurantId()).get();

        MenuItem menuItem = MenuItemTransformer.FoodRequestToFoodItem(menuRequest);

        menuItem.setRestaurant(restaurant);

        restaurant.getAvailableMenuItems().add(menuItem);

        Restaurant savedRestaurant =restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public List<MenuResponse> getMenuOfRestaurant(int id) {
        if(!validationUtils.validateRestaurant(id)) throw new RestaurantNoFoundException("Restaurant doesn't exist");
        List<MenuResponse> menu=restaurantRepository.findById(id).get()
                .getAvailableMenuItems()
                .stream()
                .map(foodItem -> MenuItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return menu;
    }

    @Override
    public List<String> getAllRestaurantsWithMoreThanGivenOrders(int noOfOrders) {
      List<String>  restaurants=restaurantRepository.getAllRestaurantsWithMoreThanGivenOrders(noOfOrders);
      if(restaurants.isEmpty()) throw new RestaurantNoFoundException("no restaurants are added in db");
        return restaurants;
    }

    @Override
    public List<String> getRestaurantsOpenedWithMostMenuItems() {
        List<String>  restaurants=restaurantRepository.getRestaurantsOpenedWithMostMenuItems();
        if(restaurants.isEmpty()) throw new RestaurantNoFoundException("no restaurants are added in db");
        return restaurants;
    }
}
