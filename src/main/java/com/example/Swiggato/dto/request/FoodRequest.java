package com.example.Swiggato.dto.request;

import com.example.Swiggato.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodRequest {

    int restaurantId;

    String dishName;

    double price;


    FoodCategory foodCategory;

    boolean veg;

}
