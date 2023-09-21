package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

       @Autowired
       CustomerService customerService;

       @PostMapping("/add-customer")
       public CustomerResponse addCustomer(@RequestBody CustomerRequest customerRequest){
               return customerService.addCustomer(customerRequest);
       }

       @GetMapping("/get-customer/mobile/{mobileNo}")
       public ResponseEntity getCustomerByMobileNo(@PathVariable String mobileNo){
              try{
                     CustomerResponse  customerResponse=customerService.getCustomerByMobileNo(mobileNo);
                     return new ResponseEntity(customerResponse, HttpStatus.FOUND);

              }catch (Exception e){
                     return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
              }


       }
}
