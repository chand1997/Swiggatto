package com.example.Swiggato.repository;

import com.example.Swiggato.enums.FoodCategory;
import com.example.Swiggato.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {

    List<MenuItem> findByFoodCategory(FoodCategory foodCategory);

    @Query(value = "SELECT fi FROM MenuItem fi WHERE fi.foodCategory = :foodCategory AND fi.restaurant.id = :restaurantId AND fi.price > :price")
    List<MenuItem> getFoodsOfParticularCategoryPriceRestaurant(FoodCategory foodCategory, double price, int restaurantId);

    @Query(value = "select fi from MenuItem fi where fi.veg=:veg and fi.restaurant.id=:restaurantId")
    List<MenuItem> getAllVegOrNonVegFoodsFromParticularRestaurant(boolean veg, int restaurantId);

    @Query(value = "select fi from MenuItem fi where fi.restaurant.id=:restaurantId order by fi.price asc limit 5")
    List<MenuItem>  getFiveCheapestFoodsFromParticularRestaurant(int restaurantId);

    @Query(value = "select fi from MenuItem fi where fi.restaurant.id=:restaurantId order by fi.price desc limit 5")
    List<MenuItem> getFiveCostliestFoodsFromParticularRestaurant(int restaurantId);

    @Query(value = "select fi from MenuItem fi where fi.foodCategory=:foodCategory order by fi.price asc limit 5")
    List<MenuItem> getFiveCheapestFoodsFromParticularCategory(FoodCategory foodCategory);

    @Query(value = "select fi from MenuItem fi where fi.foodCategory=:foodCategory order by fi.price desc limit 5")
    List<MenuItem> getFiveCostliestFoodsFromParticularCategory(FoodCategory foodCategory);
}
