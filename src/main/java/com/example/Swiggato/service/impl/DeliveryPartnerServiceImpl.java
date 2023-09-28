package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.exception.DeliveryPartnerNotFoundException;
import com.example.Swiggato.model.DeliveryPartner;
import com.example.Swiggato.repository.DeliveryPartnerRepository;
import com.example.Swiggato.service.DeliveryPartnerService;
import com.example.Swiggato.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliveryPartnerRepository deliveryPartnerRepository;

    @Autowired
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository deliveryPartnerRepository) {
        this.deliveryPartnerRepository = deliveryPartnerRepository;
    }

    @Override
    public void addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {
        DeliveryPartner deliveryPartner= DeliveryPartnerTransformer.DeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);
        deliveryPartnerRepository.save(deliveryPartner);
    }

    @Override
    public String getPartnerWithMostDeliveries() {
       Optional<DeliveryPartner>  deliveryPartnerOptional=deliveryPartnerRepository.getPartnerWithMostDeliveries();
       if(deliveryPartnerOptional.isEmpty()) throw new DeliveryPartnerNotFoundException("no partner in db");
       DeliveryPartner deliveryPartner=deliveryPartnerOptional.get();
        return deliveryPartner.getName()+" is the DeliveryPartner with most orders i.e "+deliveryPartner.getOrders().size();
    }
}
