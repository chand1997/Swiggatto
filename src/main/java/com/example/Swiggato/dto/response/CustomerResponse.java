package com.example.Swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    String name;


    String email;

    String address;

    CartResponse cart;
}
