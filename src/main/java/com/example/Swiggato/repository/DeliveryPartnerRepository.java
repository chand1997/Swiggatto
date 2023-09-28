package com.example.Swiggato.repository;

import com.example.Swiggato.model.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner,Integer> {
    @Query(value = "SELECT e FROM DeliveryPartner e WHERE e.available = true ORDER BY FUNCTION('RAND') LIMIT 1")
    DeliveryPartner getRandomPartner();

    @Query(value = "select d from DeliveryPartner d order by size(d.orders) desc limit 1")
    Optional<DeliveryPartner> getPartnerWithMostDeliveries();
}
