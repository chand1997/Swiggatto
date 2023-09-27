package com.example.Swiggato.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    double cartTotal;

    List<MenuResponse> food=new ArrayList<>();
}
