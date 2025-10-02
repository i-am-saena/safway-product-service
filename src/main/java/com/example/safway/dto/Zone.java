package com.example.safway.dto;

import lombok.Data;

@Data
public class Zone {

    private String postalCode;
    private int zoneId;
    private String alias;
    private boolean active;
}
