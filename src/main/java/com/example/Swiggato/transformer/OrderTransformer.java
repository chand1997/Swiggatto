package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodResponse;
import com.example.Swiggato.dto.response.OrderResponse;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.DeliveryPartner;
import com.example.Swiggato.model.OrderEntity;
import com.example.Swiggato.model.Restaurant;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderTransformer {


    public static OrderEntity prepareOrderEntity(Cart cart, DeliveryPartner deliveryPartner, Restaurant restaurant){
        return OrderEntity.builder()
                .customer(cart.getCustomer())
                .orderId(UUID.randomUUID().toString())
                .orderTotal(cart.getCartTotal())
                .deliveryPartner(deliveryPartner)
                .restaurant(restaurant)
                .build();
    }

    public static OrderResponse OrderEntityToOrderResponse(OrderEntity orderEntity){

        List<FoodResponse> foodResponseList=orderEntity.getFoodItemList().stream()
                .map(foodItem -> FoodItemTransformer.FoodItemToFoodResponse(foodItem))
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .customerMobile(orderEntity.getCustomer().getMobileNo())
                .orderTotal(orderEntity.getOrderTotal())
                .orderTime(orderEntity.getOrderTime())
                .foodResponses(foodResponseList)
                .deliveryPartnerMobile(orderEntity.getDeliveryPartner().getMobileNo())
                .restaurantName(orderEntity.getFoodItemList().get(0).getMenuItem().getRestaurant().getName())
                .deliveryPartnerName(orderEntity.getDeliveryPartner().getName())
                .orderId(orderEntity.getOrderId())
                .customerName(orderEntity.getCustomer().getName())
                .build();
    }
}
