package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.model.Customer;

import java.util.ArrayList;

public class CustomerTransformer {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
            return Customer.builder()
                    .name(customerRequest.getName())
                    .email(customerRequest.getEmail())
                    .address(customerRequest.getAddress())
                    .mobileNo(customerRequest.getMobileNo())
                    .gender(customerRequest.getGender())
                    .orders(new ArrayList<>())
                    .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){


        CartResponse cartResponse= CartTransformer.CartToCartResponse(customer.getCart());

        return CustomerResponse.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .cart(cartResponse)
                .build();

    }
}
