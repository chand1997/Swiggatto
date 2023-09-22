package com.example.Swiggato.controller;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/get-foods-Of-category")
    public ResponseEntity getFoodsOfCategory(@RequestParam FoodCategory foodCategory){
        List<FoodResponse> response=foodItemService.getFoodsOfCategory(foodCategory);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/get-foods-Of-category-price-restaurant")
    public ResponseEntity getFoodsOfParticularCategoryPriceRestaurant(@RequestParam FoodCategory foodCategory,
                                                                      @RequestParam double price,
                                                                      @RequestParam int restaurantId){
        List<FoodResponse> response=foodItemService
                .getFoodsOfParticularCategoryPriceRestaurant(foodCategory,price,restaurantId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/get-veg-Or-nonVeg-from-particular-restaurant")
    public ResponseEntity getAllVegOrNonVegFoodsFromParticularRestaurant(@RequestParam boolean veg,
                                                                         @RequestParam int restaurantId){
    List<FoodResponse>  response =foodItemService.getAllVegOrNonVegFoodsFromParticularRestaurant(veg,restaurantId);

    return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/get-five-cheapest-Or-costliest-foods-from-particular-restaurant")
    public ResponseEntity getFiveCheapestOrCostliestFoodsFromParticularRestaurant(@RequestParam boolean cheapest,
                                                                                  @RequestParam int restaurantId){
        List<FoodResponse> response=foodItemService.
                getFiveCheapestOrCostliestFoodsFromParticularRestaurant(cheapest,restaurantId);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/get-five-cheapest-Or-costliest-foods-from-particular-category")
    public ResponseEntity getFiveCheapestOrCostliestFoodsFromParticularCategory(@RequestParam boolean cheapest,
                                                                                  @RequestParam FoodCategory foodCategory){
        List<CostliestOrCheapestFoodResponse> response=foodItemService.
                getFiveCheapestOrCostliestFoodsFromParticularCategory(cheapest,foodCategory);
        return new ResponseEntity(response,HttpStatus.OK);
    }


}
