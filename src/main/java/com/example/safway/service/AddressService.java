package com.example.safway.service;

import com.example.safway.dto.AddressDetails;
import com.example.safway.dto.AddressDetailsResponse;

import java.util.List;

public interface AddressService {

    public List<AddressDetailsResponse> getAddressDetails(String postalCode,Boolean isBlackListed);

}
