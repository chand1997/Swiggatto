package com.example.Swiggato.repository;

import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {

    List<FoodItem> findByFoodCategory(FoodCategory foodCategory);

    @Query(value = "SELECT fi FROM FoodItem fi WHERE fi.foodCategory = :foodCategory AND fi.restaurant.id = :restaurantId AND fi.price > :price")
    List<FoodItem> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory, double price, int restaurantId);

    @Query(value = "select fi from FoodItem fi where fi.veg=:veg and fi.restaurant.id=:restaurantId")
    List<FoodItem> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId);

    @Query(value = "select fi from FoodItem fi where fi.restaurant.id=:restaurantId order by fi.price asc limit 5")
    List<FoodItem>  getFiveCheapestFoodsFromParticularRestaurant(int restaurantId);

    @Query(value = "select fi from FoodItem fi where fi.restaurant.id=:restaurantId order by fi.price desc limit 5")
    List<FoodItem> getFiveCostliestFoodsFromParticularRestaurant(int restaurantId);

    @Query(value = "select fi from FoodItem fi where fi.foodCategory=:foodCategory order by fi.price asc limit 5")
    List<FoodItem> getFiveCheapestFoodsFromParticularCategory(FoodCategory foodCategory);

    @Query(value = "select fi from FoodItem fi where fi.foodCategory=:foodCategory order by fi.price desc limit 5")
    List<FoodItem> getFiveCostliestFoodsFromParticularCategory(FoodCategory foodCategory);
}
