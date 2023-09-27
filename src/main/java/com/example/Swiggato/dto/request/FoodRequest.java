package com.example.Swiggato.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodRequest{

    int requiredQuantity;

    String customerMobile;

    int menuItemId;

}
