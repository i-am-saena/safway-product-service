package com.example.safway.service;

import com.example.safway.controller.AddressController;
import com.example.safway.dto.AddressDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchAddressTest {

    @Autowired
    AddressController addressController;

    @Test
    @DisplayName("If both params are empty, blacklisted address should not be in response")
    void testScenario1() throws IOException {
        List<AddressDetails> addresses = addressController.fetchAddressBasedOnPostalCode(null, false);
        boolean isBlacklistedAddressPresent = addresses.stream().anyMatch(address -> address.getPostalCode().equals("435 ABD") || address.getPostalCode().equals("456 VTG") || address.getPostalCode().equals("985 TYK"));
       assertFalse(isBlacklistedAddressPresent);
    }

    @Test
    @DisplayName("If blacklistedAddress is true and postalCode is not passed, blacklisted address should return in response")
    void testScenario2() throws IOException {
        List<AddressDetails> addresses = addressController.fetchAddressBasedOnPostalCode(null, true);
        boolean isBlacklistedAddressPresent = addresses.stream().anyMatch(address -> address.getPostalCode().equals("435 ABD") || address.getPostalCode().equals("456 VTG") || address.getPostalCode().equals("985 TYK"));
        assertTrue(isBlacklistedAddressPresent);
    }

    @Test
    @DisplayName("Postal code supplied with blacklistAddress as false, Address with same postal code should return if it is not blacklisted")
    void testScenario3() throws IOException {
        List<AddressDetails> addresses = addressController.fetchAddressBasedOnPostalCode("222 BBB", false);
        boolean isAddressReturnedWithSamePostCode = addresses.stream().anyMatch(address -> address.getPostalCode().equals("222 BBB"));
        assertTrue(isAddressReturnedWithSamePostCode);
    }

    @Test
    @DisplayName("black listed Postal code supplied with blacklistAddress as false, No address should return")
    void testScenario4() throws IOException {
        List<AddressDetails> addresses = addressController.fetchAddressBasedOnPostalCode("985 TYK", false);
        assertTrue(addresses.isEmpty());
    }

    @Test
    @DisplayName("black listed Postal code supplied with blacklistAddress as true, ddress with same postal code should return")
    void testScenario5() throws IOException {
        List<AddressDetails> addresses = addressController.fetchAddressBasedOnPostalCode("985 TYK", true);
        assertFalse(addresses.isEmpty());
    }
}