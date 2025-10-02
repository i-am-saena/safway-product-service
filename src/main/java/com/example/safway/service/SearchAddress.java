package com.example.safway.service;

import com.example.safway.dto.AddressDetails;
import com.example.safway.dto.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.safway.repository.AddressDetailsRepository.getAllAddresses;

@Service
public class SearchAddress {


    BlacklistAddress blacklistAddress;

    @Autowired
    public SearchAddress(BlacklistAddress blacklistAddress) {
        this.blacklistAddress = blacklistAddress;
    }

    public List<AddressDetails> getAddressByPastalCode(String postalCode, Boolean blacklistedAddress) throws IOException {

        List<AddressDetails> responceList = new ArrayList<>();
        List<String> blackListpostalCode = blacklistAddress.getAll().stream().map(Zone::getPostalCode).toList();
        if (postalCode == null) {

            responceList = getAddressIfPostalCodeIsNull(blacklistedAddress, responceList, blackListpostalCode);
        } else {
            responceList = getAddressIfPostalCodeIsNotNull(blacklistedAddress, responceList, blackListpostalCode, postalCode);

        }
        return responceList;

    }

    private List<AddressDetails> getAddressIfPostalCodeIsNull(boolean blacklistedAddress, List<AddressDetails> responceList, List<String> blackListpostalCode) {
        if (blacklistedAddress) {
            responceList.addAll(getAllAddresses());

            return responceList;
        } else {


            responceList = getAllAddresses()
                    .stream()
                    .filter(f -> !blackListpostalCode.contains(f.getPostalCode())).collect(Collectors.toList());
            return responceList;

        }
    }

    public List<AddressDetails> getAddressIfPostalCodeIsNotNull(boolean blacklistedAddress, List<AddressDetails> responceList, List<String> blackListpostalCode, String postalCode) {

        if (blacklistedAddress) {
            responceList.addAll(getAllAddresses());
            responceList = getAllAddresses().stream().filter(f -> f.getPostalCode().contains(postalCode)).collect(Collectors.toList());
            return responceList;
        } else {
            if (!blackListpostalCode.contains(postalCode)) {
                responceList = getAllAddresses().stream().filter(f -> f.getPostalCode().equals(postalCode)).collect(Collectors.toList());
                return responceList;
            }
        }
        return responceList;
    }


}
