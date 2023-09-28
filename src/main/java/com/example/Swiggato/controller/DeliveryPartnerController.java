package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partner")
public class DeliveryPartnerController {

    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")
    public ResponseEntity addPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){
        deliveryPartnerService.addPartner(deliveryPartnerRequest);
        return new ResponseEntity("DeliveryPartner added", HttpStatus.CREATED);
    }

    @GetMapping("/get-partner-with-most-deliveries")
    public ResponseEntity getPartnerWithMostDeliveries(){
       String message=deliveryPartnerService.getPartnerWithMostDeliveries();
       return new ResponseEntity(message,HttpStatus.FOUND);
    }

}
