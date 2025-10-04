package com.example.safway.dto;

import lombok.Data;

import java.util.Map;
@Data
public class CartDetails {

    Map<Long, Integer> items;
    String coupon;


}
