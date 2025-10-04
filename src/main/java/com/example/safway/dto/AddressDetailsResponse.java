package com.example.safway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDetailsResponse {
    private String postalCode;
    private String street;
    private String city;
    private boolean blacklisted;
}
