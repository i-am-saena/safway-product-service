package com.example.safway.donottouch;

import lombok.Data;

@Data
public class Cupon {
    private String couponName;
    private String couponCategory;
    private double discountedPrice;
}
