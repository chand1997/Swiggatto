package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.model.Cart;
import com.example.Swiggato.model.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.service.CustomerService;
import com.example.Swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
//        CustomerRequest to Customer
        Customer customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);

//        building cart
        Cart cart= Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .foodItems(new ArrayList<>())
                .build();

//       adding cart o customer
        customer.setCart(cart);

//        saving customer and cart in db
        Customer savedCustomer=customerRepository.save(customer);

//        Customer To CustomerResponse
       return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);

    }

    @Override
    public CustomerResponse getCustomerByMobileNo(String mobileNo) {
       Optional<Customer> optionalCustomer=customerRepository.findByMobileNo(mobileNo);
       if(optionalCustomer.isEmpty()) throw new CustomerNotFoundException("Wrong Mobile Number");
       return CustomerTransformer.CustomerToCustomerResponse(optionalCustomer.get());
    }
}
