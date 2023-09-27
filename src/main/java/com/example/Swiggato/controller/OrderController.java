package com.example.Swiggato.controller;

import com.example.Swiggato.dto.response.OrderResponse;
import com.example.Swiggato.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestParam String customerMobile){
         OrderResponse orderResponse=orderService.placeOrder(customerMobile);
         return new ResponseEntity(orderResponse, HttpStatus.CREATED);
    }
}
