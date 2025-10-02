package com.example.safway.controller;

import com.example.safway.dto.AddressDetails;
import com.example.safway.service.SearchAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class AddressController {


    SearchAddress searchAddress;

    @Autowired
    public AddressController(SearchAddress searchAddress) {
        this.searchAddress = searchAddress;
    }

    @GetMapping("/api/address/search")
    public List<AddressDetails> fetchAddressBasedOnPostalCode(@RequestParam(required = false)  String postalCode, @RequestParam(required = false, defaultValue = "false") Boolean blacklistedAddress) throws IOException {

        return searchAddress.getAddressByPastalCode(postalCode,blacklistedAddress);
    }
}
