package com.example.Swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostliestOrCheapestFoodResponse {

    String dishName;

    String restaurantName;
}
