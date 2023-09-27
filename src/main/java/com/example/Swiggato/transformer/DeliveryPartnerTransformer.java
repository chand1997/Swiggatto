package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.model.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {


    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(DeliveryPartnerRequest deliveryPartnerRequest){
        return DeliveryPartner.builder()
                .gender(deliveryPartnerRequest.getGender())
                .orders(new ArrayList<>())
                .name(deliveryPartnerRequest.getName())
                .available(true)
                .mobileNo(deliveryPartnerRequest.getMobileNo())
                .build();
    }
}
