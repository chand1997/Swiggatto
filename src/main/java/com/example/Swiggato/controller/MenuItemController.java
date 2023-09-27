package com.example.Swiggato.controller;

import com.example.Swiggato.dto.response.CostliestOrCheapestFoodResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.service.MenuItemService;
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
public class MenuItemController {

    final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/get-foods-Of-category")
    public ResponseEntity getFoodsOfCategory(@RequestParam FoodCategory foodCategory){
        List<MenuResponse> response= menuItemService.getFoodsOfCategory(foodCategory);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/get-foods-Of-category-price-restaurant")
    public ResponseEntity getFoodsOfParticularCategoryPriceRestaurant(@RequestParam FoodCategory foodCategory,
                                                                      @RequestParam double price,
                                                                      @RequestParam int restaurantId){
        List<MenuResponse> response= menuItemService
                .getFoodsOfParticularCategoryPriceRestaurant(foodCategory,price,restaurantId);
        return new ResponseEntity(response, HttpStatus.OK);
    }


    @GetMapping("/get-veg-Or-nonVeg-from-particular-restaurant")
    public ResponseEntity getAllVegOrNonVegFoodsFromParticularRestaurant(@RequestParam boolean veg,
                                                                         @RequestParam int restaurantId){
    List<MenuResponse>  response = menuItemService.getAllVegOrNonVegFoodsFromParticularRestaurant(veg,restaurantId);

    return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/get-five-cheapest-Or-costliest-foods-from-particular-restaurant")
    public ResponseEntity getFiveCheapestOrCostliestFoodsFromParticularRestaurant(@RequestParam boolean cheapest,
                                                                                  @RequestParam int restaurantId){
        List<MenuResponse> response= menuItemService.
                getFiveCheapestOrCostliestFoodsFromParticularRestaurant(cheapest,restaurantId);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    @GetMapping("/get-five-cheapest-Or-costliest-foods-from-particular-category")
    public ResponseEntity getFiveCheapestOrCostliestFoodsFromParticularCategory(@RequestParam boolean cheapest,
                                                                                  @RequestParam FoodCategory foodCategory){
        List<CostliestOrCheapestFoodResponse> response= menuItemService.
                getFiveCheapestOrCostliestFoodsFromParticularCategory(cheapest,foodCategory);
        return new ResponseEntity(response,HttpStatus.OK);
    }


}
