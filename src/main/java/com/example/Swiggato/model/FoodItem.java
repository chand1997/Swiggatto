package com.example.Swiggato.model;

import com.example.Swiggato.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "food_item")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String dishName;

    double price;

    @Enumerated(EnumType.STRING)
    FoodCategory foodCategory;

    boolean veg;

    boolean available;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity;
}
