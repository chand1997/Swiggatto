package com.example.Swiggato.controller;

import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    final FoodItemService foodItemService;

    @Autowired
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/get-most-ordered-foodCategory")
    public ResponseEntity getMostOrderedFoodCategory(){
     FoodCategory foodCategory= foodItemService.getMostOrderedFoodCategory();
     return new ResponseEntity(foodCategory+" is the most ordered", HttpStatus.FOUND);
    }
}
