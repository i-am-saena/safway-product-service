package com.example.safway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDetails {

    private String postalCode;
    private String street ;
    private String city;
}
