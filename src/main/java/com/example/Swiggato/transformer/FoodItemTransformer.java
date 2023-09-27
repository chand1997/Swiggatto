package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.FoodRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.FoodItem;
import com.example.Swiggato.model.MenuItem;
import com.example.Swiggato.model.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class FoodItemTransformer {

    public static FoodItem FoodRequestToFoodItem(FoodRequest foodRequest, Cart cart, MenuItem menuItem){
              return FoodItem.builder()
                      .cart(cart)
                      .menuItem(menuItem)
                      .totalCost(foodRequest.getRequiredQuantity()* menuItem.getPrice())
                      .requiredQuantity(foodRequest.getRequiredQuantity())
                      .build();
    }

    public static CartStatusResponse CartToCartStatusResponse(Cart cart, Restaurant restaurant){
        System.out.println(cart.getFoodItems() + "in transformer");
        List<FoodResponse> foodResponseList=cart.getFoodItems()
                .stream()
                .map(foodItem -> FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        double price=0;
        for(FoodItem foodItem: cart.getFoodItems()){
            price+=foodItem.getTotalCost();
        }



        return CartStatusResponse.builder()
                .restaurantName(restaurant.getName())
                .customerAddress(cart.getCustomer().getAddress())
                .customerName(cart.getCustomer().getName())
                .customerMobile(cart.getCustomer().getMobileNo())
                .cartTotal(price)
                .foodList(foodResponseList)
                .build();
    }

    public static FoodResponse FoodItemToFoodResponse(FoodItem foodItem){
        return FoodResponse.builder()
                .dishName(foodItem.getMenuItem().getDishName())
                .price(foodItem.getMenuItem().getPrice())
                .veg(foodItem.getMenuItem().isVeg())
                .category(foodItem.getMenuItem().getFoodCategory())
                .quantityAdded(foodItem.getRequiredQuantity())
                .build();
    }

}
