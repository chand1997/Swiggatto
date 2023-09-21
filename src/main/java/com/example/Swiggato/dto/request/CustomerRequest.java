package com.example.Swiggato.dto.request;

import com.example.Swiggato.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    String name;


    String email;

    String address;


    String mobileNo;


    Gender gender;
}
