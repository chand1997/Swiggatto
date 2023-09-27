package com.example.Swiggato.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_entity")
public class OrderEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   int id;

   String orderId;

   double orderTotal;

   @CreationTimestamp
   Date orderTime;

   @ManyToOne
   @JoinColumn
   Customer customer;

   @ManyToOne
   @JoinColumn
   DeliveryPartner deliveryPartner;

   @ManyToOne
   @JoinColumn
   Restaurant restaurant;

   @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
   List<FoodItem> foodItemList=new ArrayList<>();


}
