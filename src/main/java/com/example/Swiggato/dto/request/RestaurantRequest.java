package com.example.Swiggato.dto.request;

import com.example.Swiggato.enums.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantRequest {

    String name;

    String location;


    RestaurantCategory restaurantCategory;



    String contactNo;



}
