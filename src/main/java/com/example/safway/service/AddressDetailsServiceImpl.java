package com.example.safway.service;

import com.example.safway.AddressDetailsRepository;
import com.example.safway.dto.AddressDetails;
import com.example.safway.dto.AddressDetailsResponse;
import com.example.safway.dto.Zone;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressDetailsServiceImpl implements AddressService{


    BlacklistAddress blacklistAddress;
    @SneakyThrows
    public List<AddressDetailsResponse> getAddressDetails(String postalCode,Boolean isBlackListed){
       // List<AddressDetailsResponse> finalAddressDetailsResponses = new ArrayList<>();
        List<AddressDetails> allAddresses = AddressDetailsRepository.getAllAddresses();
        blacklistAddress = new BlacklistAddress();
       // List<Zone> Zone = blacklistAddress.getAll();
        List<AddressDetailsResponse> allAddr = allAddresses.stream().map(addr -> new AddressDetailsResponse(addr.getPostalCode(),
                addr.getStreet(), addr.getStreet(), false)).toList();
        if(isBlackListed){
            List<AddressDetailsResponse> blackListed = blacklistAddress.getAll().stream().map(addr -> new AddressDetailsResponse(
                    addr.getPostalCode(), null, null, true)).toList();
            allAddr.addAll(blackListed);
        }
        if(postalCode != null && !postalCode.isEmpty()){
            allAddr.stream().filter(addr -> addr.getPostalCode().equals(postalCode)).toList();

        }

        return allAddr;
    }
}
