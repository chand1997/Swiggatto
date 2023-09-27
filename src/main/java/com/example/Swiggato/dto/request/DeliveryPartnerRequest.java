package com.example.Swiggato.dto.request;

import com.example.Swiggato.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryPartnerRequest {

    String name;

    String mobileNo;


    Gender gender;

}
