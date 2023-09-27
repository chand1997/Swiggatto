package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.response.OrderResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.EmptyCartException;
import com.example.Swiggato.model.*;
import com.example.Swiggato.repository.*;
import com.example.Swiggato.service.OrderService;
import com.example.Swiggato.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    final OrderRepository orderRepository;

    final CustomerRepository customerRepository;

    final DeliveryPartnerRepository deliveryPartnerRepository;

    final RestaurantRepository restaurantRepository;

    final CartRepository cartRepository;

    final MenuItemRepository menuItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, DeliveryPartnerRepository deliveryPartnerRepository, RestaurantRepository restaurantRepository, CartRepository cartRepository, MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.deliveryPartnerRepository = deliveryPartnerRepository;
        this.restaurantRepository = restaurantRepository;
        this.cartRepository = cartRepository;
        this.menuItemRepository = menuItemRepository;
    }


    @Override
    public OrderResponse placeOrder(String customerMobile) {
          Optional<Customer> optionalCustomer= customerRepository.findByMobileNo(customerMobile);
          if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("invalid mobile no!!");

          Customer customer=optionalCustomer.get();
          if(customer.getCart().getFoodItems().isEmpty()) throw new EmptyCartException("cart is empty");

        DeliveryPartner deliveryPartner=deliveryPartnerRepository.getRandomPartner();
        Restaurant restaurant=customer.getCart().getFoodItems().get(0).getMenuItem().getRestaurant();

        OrderEntity orderEntity= OrderTransformer.prepareOrderEntity(customer.getCart(),deliveryPartner,restaurant);

        OrderEntity savedOrder=orderRepository.save(orderEntity);
        savedOrder.setFoodItemList(customer.getCart().getFoodItems());

        restaurant.getOrders().add(savedOrder);
        deliveryPartner.getOrders().add(savedOrder);
        customer.getOrders().add(savedOrder);
        deliveryPartner.setAvailable(false);

        List<FoodItem> foodItemList=customer.getCart().getFoodItems();
        for(FoodItem f:foodItemList){
            f.setCart(null);
            f.setOrderEntity(savedOrder);
        }
        customer.getCart().setFoodItems(new ArrayList<>());
        customer.getCart().setCartTotal(0);

        restaurantRepository.save(restaurant);
        deliveryPartnerRepository.save(deliveryPartner);
        customerRepository.save(customer);

        return OrderTransformer.OrderEntityToOrderResponse(savedOrder);

    }
}
