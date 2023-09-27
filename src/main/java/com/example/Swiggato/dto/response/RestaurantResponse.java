package com.example.Swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {
    String name;

    String location;


    String contactNo;


    boolean opened;


    List<MenuResponse> menu;
}
