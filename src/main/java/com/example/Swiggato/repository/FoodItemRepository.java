package com.example.Swiggato.repository;

import com.example.Swiggato.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    String queryToDeleteFoodItemsOfPreviousRestaurant="delete from FoodItem fe where fe.cart.id=:cartId";

    @Modifying
    @Query(value = queryToDeleteFoodItemsOfPreviousRestaurant)
    void deleteFoodItemsOfPreviousRestaurant(int cartId);


}
