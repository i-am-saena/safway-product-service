package com.example.safway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class Product {
    @NotBlank
    private String name;
    @NotNull
    private Double price;
    @NotBlank
    private String category;

}
