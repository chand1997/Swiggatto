package com.example.Swiggato.model;

import com.example.Swiggato.enums.RestaurantCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;

    String location;

    @Enumerated(EnumType.STRING)
    RestaurantCategory restaurantCategory;

//    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    @Size(min = 10,max=10)
    @Column(unique = true,nullable = false)
    String contactNo;


    boolean opened;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<FoodItem> availableFoodItems=new ArrayList<>();

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();
}
