package com.example.safway.controller;

import com.example.safway.dto.AddressDetailsResponse;
import com.example.safway.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/search")
    public ResponseEntity<List<AddressDetailsResponse>> serachAddress(@RequestParam(value = "postalcode")String postalCode,
                                                                      @RequestParam(value = "baddr") Boolean isblacklisted) {

        List<AddressDetailsResponse> addressDetails = addressService.getAddressDetails(postalCode, isblacklisted);
        return ResponseEntity.ok(addressDetails);
    }
}
