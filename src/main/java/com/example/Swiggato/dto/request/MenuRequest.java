package com.example.Swiggato.dto.request;

import com.example.Swiggato.enums.FoodCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuRequest {

    int restaurantId;

    String dishName;

    double price;


    FoodCategory foodCategory;

    boolean veg;

}
