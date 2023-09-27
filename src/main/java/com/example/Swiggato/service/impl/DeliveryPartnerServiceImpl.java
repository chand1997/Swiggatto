package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.model.DeliveryPartner;
import com.example.Swiggato.repository.DeliveryPartnerRepository;
import com.example.Swiggato.service.DeliveryPartnerService;
import com.example.Swiggato.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
