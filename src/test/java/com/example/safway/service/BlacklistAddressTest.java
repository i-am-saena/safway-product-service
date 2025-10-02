package com.example.safway.service;

import com.example.safway.dto.Zone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistAddressTest {

    private BlacklistAddress blacklistAddress;

    @BeforeEach
    void setUp() {
        blacklistAddress = new BlacklistAddress();
    }

    @Test
    void testGetAllReadsExpectedPostalCodes() {
        try {
            List<Zone> zones = blacklistAddress.getAll();

            assertNotNull(zones, "Zones list should not be null");
            assertFalse(zones.isEmpty(), "Zones list should not be empty");

            // Extract postal codes from the list
            List<String> postalCodes = zones.stream()
                    .map(Zone::getPostalCode)
                    .collect(Collectors.toList());

            // Check that the expected postal codes exist in the list
            assertTrue(postalCodes.contains("435 ABD"), "Postal code 435 ABD should be present");
            assertTrue(postalCodes.contains("456 VTG"), "Postal code 456 VTG should be present");
            assertTrue(postalCodes.contains("985 TYK"), "Postal code 985 TYK should be present");

            // Optionally, check the list size is at least 3
            assertTrue(zones.size() >= 3, "Zones list should have at least 3 entries");

        } catch (IOException e) {
            fail("IOException occurred while reading zones.json: " + e.getMessage());
        }
    }
}