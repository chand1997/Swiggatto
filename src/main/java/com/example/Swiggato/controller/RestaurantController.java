package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController{

    final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
           RestaurantResponse restaurantResponse=restaurantService.addRestaurant(restaurantRequest);
           return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }

    @PutMapping("/change-status")
    public String changeOpenedStatus(@RequestParam int id){
        return restaurantService.changeOpenedStatus(id);
    }

    @PostMapping("/add-foodItem")
    public ResponseEntity addFoodItemToRestaurant(@RequestBody FoodRequest addFoodToMenuRequest){
       RestaurantResponse restaurantResponse= restaurantService.addFoodItemToRestaurant(addFoodToMenuRequest);
       return new ResponseEntity(restaurantResponse,HttpStatus.CREATED);
    }


    @GetMapping("/get-menu")
    public ResponseEntity getMenuOfRestaurant(@RequestParam int id){
        List<FoodResponse> menu=restaurantService.getMenuOfRestaurant(id);
        return new ResponseEntity(menu,HttpStatus.FOUND);
    }





















}
