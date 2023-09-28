package com.example.Swiggato.repository;

import com.example.Swiggato.enums.Gender;
import com.example.Swiggato.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Optional<Customer> findByMobileNo(String mobile);

    @Query(value = "select c from Customer c order by size(c.orders) desc limit 1")
    Optional<Customer> getCustomerWithMostOrders();

    @Query(value = "select c from Customer c where c.gender=:gender order by size(c.orders) asc limit 1")
    Optional<Customer> getCustomerWithLeastOrdersByGender(Gender gender);

}
