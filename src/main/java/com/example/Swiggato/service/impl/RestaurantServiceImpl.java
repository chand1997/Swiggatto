package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNoFoundException;
import com.example.Swiggato.model.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.RestaurantService;
import com.example.Swiggato.transformer.RestaurantTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {


    final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest){
        Restaurant restaurant= RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);

        Restaurant savedRestaurant=restaurantRepository.save(restaurant);

        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    @Override
    public String changeOpenedStatus(int id) {
       Optional<Restaurant>  optionalRestaurant=restaurantRepository.findById(id);

       if(optionalRestaurant.isEmpty()) throw new RestaurantNoFoundException("Invalid id");

       Restaurant restaurant=optionalRestaurant.get();

       restaurant.setOpened(!restaurant.isOpened());

       Restaurant savedRestaurant= restaurantRepository.save(restaurant);

       if(savedRestaurant.isOpened()) return "Restaurant is Opened Now!!";
        return "Restaurant is Closed Now!!";
    }
}
