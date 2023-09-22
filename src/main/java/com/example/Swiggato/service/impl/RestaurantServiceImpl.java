package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNoFoundException;
import com.example.Swiggato.model.FoodItem;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.RestaurantService;
import com.example.Swiggato.transformer.FoodItemTransformer;
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
    public RestaurantResponse addFoodItemToRestaurant(FoodRequest foodRequest) {

        if(!validationUtils.validateRestaurant(foodRequest.getRestaurantId()))
            throw new RestaurantNoFoundException("Restaurant doesn't exist!!");

        Restaurant restaurant=restaurantRepository.findById(foodRequest.getRestaurantId()).get();

        FoodItem foodItem= FoodItemTransformer.FoodRequestToFoodItem(foodRequest);

        foodItem.setRestaurant(restaurant);

        restaurant.getAvailableFoodItems().add(foodItem);

        Restaurant savedRestaurant =restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    @Override
    public List<FoodResponse> getMenuOfRestaurant(int id) {
        if(!validationUtils.validateRestaurant(id)) throw new RestaurantNoFoundException("Restaurant doesn't exist");
        List<FoodResponse> menu=restaurantRepository.findById(id).get()
                .getAvailableFoodItems()
                .stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());
        return menu;
    }
}
