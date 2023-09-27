package com.example.Swiggato.model;

import com.example.Swiggato.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
//@Data
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Size(min = 3, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;

    @Email(message = "Please provide a valid email address")
    @Column(unique = true)
    String email;

    String address;

//    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid mobile number")
    @Size(min = 10,max=10)
    String mobileNo;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();
}
