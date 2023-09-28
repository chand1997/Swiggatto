package com.example.Swiggato.service.impl;

import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.exception.FoodItemNotFoundException;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    final FoodItemRepository foodItemRepository;

    @Autowired
    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public FoodCategory getMostOrderedFoodCategory() {
      Optional<FoodCategory>  optionalFoodCategory=foodItemRepository.getMostOrderedFoodCategory();
      if(optionalFoodCategory.isEmpty()) throw new FoodItemNotFoundException("No foodItem is added to db");
      return optionalFoodCategory.get();
    }
}
