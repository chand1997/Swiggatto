package com.example.Swiggato.repository;

import com.example.Swiggato.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Query(value = "select r.name from Restaurant r where size(r.orders)>:noOfOrders")
    List<String> getAllRestaurantsWithMoreThanGivenOrders(int noOfOrders);

    @Query(value = "select r.name from Restaurant r where r.opened=true order by size(r.availableMenuItems) desc")
    List<String> getRestaurantsOpenedWithMostMenuItems();
}
