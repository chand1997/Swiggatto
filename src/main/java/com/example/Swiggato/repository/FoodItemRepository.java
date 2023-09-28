package com.example.Swiggato.repository;

import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    String queryToDeleteFoodItemsOfPreviousRestaurant="delete from FoodItem fe where fe.cart.id=:cartId";

    @Modifying
    @Query(value = queryToDeleteFoodItemsOfPreviousRestaurant)
    void deleteFoodItemsOfPreviousRestaurant(int cartId);


    @Query(value = "select f.menuItem.foodCategory from FoodItem f group by f.menuItem.foodCategory order by count(f) desc limit 1")
    Optional<FoodCategory> getMostOrderedFoodCategory();
}
