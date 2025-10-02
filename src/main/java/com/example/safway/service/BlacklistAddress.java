package com.example.safway.service;

import com.example.safway.dto.Zone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class BlacklistAddress {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Zone> getAll() throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("blacklistResponse.json")) {

            if (inputStream == null) {
                throw new IOException("zones.json not found in resources folder!");
            }

            List<Zone> zones = objectMapper.readValue(inputStream, new TypeReference<List<Zone>>() {
            });

            // just for fun, assign random "confusing" fields
            for (int i = 0; i < zones.size(); i++) {
                Zone z = zones.get(i);
                z.setZoneId(i + 100);
                z.setAlias("Alias-" + i);
                z.setActive(i % 2 == 0);
            }

            return zones;
        }
    }

}
