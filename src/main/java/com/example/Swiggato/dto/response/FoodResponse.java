package com.example.Swiggato.dto.response;


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
public class FoodResponse {

    String dishName;

    double price;

    FoodCategory category;

    boolean veg;

    int quantityAdded;
}
