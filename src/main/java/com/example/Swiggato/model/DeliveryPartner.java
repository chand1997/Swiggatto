package com.example.Swiggato.model;

import com.example.Swiggato.enums.Gender;
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
@Table(name = "delivery_partner")
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min=3,max=40,message = "Name should be between 3 to 40 characters.")
    String name;

//    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
     @Size(min = 10,max=10)
     @Column(unique = true,nullable = false)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner",cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();
}
